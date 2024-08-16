package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PersonAccountTest {

    @Test
    void calculateNetWorthAndBalanceTest() {
        System.out.println("Hello world!");
        List<PersonAccount> personAccountList = new ArrayList<PersonAccount>();
        personAccountList.add(new PersonAccount("sim",500L,0));
        personAccountList.add(new PersonAccount("tim",200L,0));
        personAccountList.add(new PersonAccount("bill",0L,0));

        List<String> list= new ArrayList<String>();
        list.add("tim");
        list.add("bill");

        System.out.println("Initial Balance for the persons:"+ personAccountList);

        List<PersonAccount> run1 = PersonAccount.calculateNetBalanceAndAsset("sim", 500L, list, personAccountList);
        System.out.println("After spending on camping equipment by sim:"+run1);

        List<PersonAccount> run2 = PersonAccount.calculateNetBalanceAndAsset("tim", 200L, list, run1);
        System.out.println("After spending on food for camping by tim:"+run2);

        List<PersonAccount> output1= new ArrayList<PersonAccount>();
        List<PersonAccount> output2= new ArrayList<PersonAccount>();
        List<PersonAccount> output3= new ArrayList<PersonAccount>();
        output1.add( new PersonAccount("sim",0L,0));
        output2.add( new PersonAccount("tim",0L,350L));
        output3.add( new PersonAccount("sim",0L,0L));
//        Assertion
        assertEquals( run1.stream().filter(i->i.name.equals("sim")).collect(Collectors.toList()).toString(),output1.toString());
        assertEquals( run2.stream().filter(i->i.name.equals("tim")).collect(Collectors.toList()).toString(),output2.toString());
        assertEquals( run2.stream().filter(i->i.name.equals("sim")).collect(Collectors.toList()).toString(),output3.toString());
    }
    @Test
    void exceptionRaisedWhenPersonIsNotInTheListTest() {
        System.out.println("Hello world!");
        List<PersonAccount> personAccountList2 = new ArrayList<PersonAccount>();
        personAccountList2.add(new PersonAccount("sim",500L,0));
        personAccountList2.add(new PersonAccount("tim",200L,0));
        personAccountList2.add(new PersonAccount("bill",0L,0));

        List<String> list= new ArrayList<String>();
        list.add("tim");
        list.add("bill");

        System.out.println("Initial Balance for the persons:"+ personAccountList2);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonAccount.calculateNetBalanceAndAsset("bin", 500L, list, personAccountList2);
        });

//        Assertion
        assertEquals( exception.getMessage(),"Person is not valid");
    }
}