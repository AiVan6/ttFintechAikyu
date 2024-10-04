package com.example.ttwebzaim.services;

import com.example.ttwebzaim.entitys.LoanRequest;
import com.example.ttwebzaim.entitys.RegPerson;
import com.example.ttwebzaim.entitys.VerifiedName;
import com.example.ttwebzaim.repositories.SettingsRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class StopFactorService {

    private static final Logger logger = LoggerFactory.getLogger(StopFactorService.class);
    private final SettingsRepository settingsRepository;

    @Autowired
    public StopFactorService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public boolean inputData(LoanRequest loanRequest) {

        String regPersonString = concatRegPerson(loanRequest.getRegPerson());
        String verifiedNameString = concatVerifiedName(
                loanRequest.getCreditBureau().getVerifiedName());

        return searchLevenshteinDistance(regPersonString, verifiedNameString);

    }

    public boolean searchLevenshteinDistance(String regPersonString, String verifiedNameString) {

        Double distanceThreshold = settingsRepository.findDistanceRatioThreshold();
        if (distanceThreshold == null) {
            throw new IllegalArgumentException("distanceRatioThreshold setting not found");
        }
        logger.info("distanceRatioThreshold: {}", distanceThreshold);

        List<String> regPersons = generateWord(splitWords(regPersonString));
        List<String> verifiedNames = generateWord(splitWords(verifiedNameString));

        logger.info("Сгенерированные пары для regPerson: {}", regPersons);
        logger.info("Сгенерированные пары для verifiedName: {}", verifiedNames);

        int maxDistance = findMaxLevenshteinDistance(regPersons, verifiedNames);

        logger.info("Найденное максимальное расстояние Левенштейна: {}", maxDistance);

        return maxDistance < distanceThreshold;
    }

    private String concatRegPerson(RegPerson regPerson){
        return regPerson.getMiddleName() != null ?
                regPerson.getFirstName()+ " " + regPerson.getMiddleName()  + " " + regPerson.getLastName():
                regPerson.getFirstName() + " " + regPerson.getLastName();
    }

    private String concatVerifiedName(VerifiedName verifiedName){
        return verifiedName.getOtherName() != null ?
                verifiedName.getFirstName() + " " + verifiedName.getOtherName() + " " + verifiedName.getSurname():
                verifiedName.getFirstName() + " " + verifiedName.getSurname();
    }

    private List<String> splitWords(String input){
        return List.of(input.split("\\s+"));
    }


    public List<String> generateWord(List<String> words){

        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                pairs.add(words.get(i) + words.get(j));
                logger.info("Создание пар: {}{}", words.get(i), words.get(j));
            }
        }
        return pairs;
    }

    public int findMaxLevenshteinDistance(List<String> regPersonList, List<String> verifiedNameList) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int maxDistance = 0;
        for (String regPerson : regPersonList) {
            for (String verifiedName : verifiedNameList) {
                int distance = levenshtein.apply(regPerson, verifiedName);
                logger.info("Расстояние между '{}' and '{}': {}", regPerson, verifiedName, distance);
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }
        return maxDistance;
    }
}
