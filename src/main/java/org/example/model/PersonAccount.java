package org.example.model;

import java.util.*;
import java.util.stream.Collectors;

public class PersonAccount {
    String name;
    Long initialBalance;
    long assetWorth;

    public PersonAccount(String name, Long initialBalance, long assetWorth) {
        this.name = name;
        this.initialBalance = initialBalance;
        this.assetWorth = assetWorth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", initialBalance=" + initialBalance +
                ", assetWorth=" + assetWorth +
                '}';
    }

    public static List<PersonAccount> calculateNetBalanceAndAsset(String contributor, Long contribution, List<String> distributionList, List<PersonAccount> personAccountList) {
        if (!(personAccountList.stream().filter(personAccount -> personAccount.name.equals(contributor)).collect(Collectors.toList()).size() > 0)) {
            throw new RuntimeException("Person is not valid");
        } else {
            long asset = contribution / distributionList.size();
            return personAccountList.stream()
                    .map(personAccount -> {
                        if (distributionList.contains(personAccount.name)) {
                            personAccount.assetWorth += asset;
                        }
                        if (personAccount.name.equals(contributor)) {
                            personAccount.initialBalance -= contribution;
                        }
                        return personAccount;
                    })
                    .collect(Collectors.toList());
        }
    }
}
