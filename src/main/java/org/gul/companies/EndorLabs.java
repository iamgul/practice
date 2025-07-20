package org.gul.companies;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EndorLabs {

    /*
    Kolkata:
        name : Kolkata
        temperature : 31
        unit : Celcius
        time : 15:00
        precipitations : 70%

        name : Kolkata
        temperature : 32
        unit : Celcius
        time : 16:00
        precipitations : 79%

        name : Kolkata
        temperature : 33
        unit : Celcius
        time : 17:00
        precipitations : 90%
*/


    public static void main(String[] args) {
        List<Cities> cities = Arrays.asList(
                new Cities("Kolkata", 31.0, "18:00", 60),
                new Cities("Kolkata", 32.0, "16:00", 90),
                new Cities("Kolkata", 32.5, "17:00", 70),
                new Cities("Kolkata", 31.1, "15:00", 80)
        );

        //Give me temperature of given city in ascending order of their time
        List<Double> temperatureBasedOnSortedTime = cities.stream().filter(c -> c.getName().equals("Kolkata"))
                .sorted((c1, c2) -> c1.getTime().compareTo(c2.getTime()))
                .map(c -> c.getTemperature()).toList();

        //Give List of City Name and their temperature ordered in descending order of their time
       //cities.stream().sorted((c1, c2) -> -c1.getTime().compareTo(c2.getTime())).collect(Collectors.toMap(c -> c.getName(), c -> c.getTemperature()));


        //{Kolkata=[31.0, 31.1, 32.0, 32.5]}
        //{Kolkata=[31.0, 31.1, 32.0, 32.5]}
        Map<String, List<Double>> collect = cities.stream().sorted((c1, c2) -> Double.valueOf(c1.getTemperature()).compareTo(Double.valueOf(c2.getTemperature()))).collect(Collectors.groupingBy(c -> c.getName(),LinkedHashMap::new ,Collectors.mapping(c1 -> c1.getTemperature(), Collectors.toList())));

        System.out.println(collect);
        //Map<String, List<Double>> collect

        //{Kolkata=[31.0, 32.5, 32.0, 31.1]}


        //Give List of City Name and their temperature ordered in descending order of their time and precipitation on buckets level i,e if precipitation is <70% or <80% or <90%
    }


}


    class Cities{
        private String name;
        private double temperature;
        private String unit;
        private String time;
        private int precipitations;

        public Cities(String name, double temperature, String time, int precipitations) {
            this.name = name;
            this.temperature = temperature;
            this.time = time;
            this.precipitations = precipitations;
        }

        public Cities() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getPrecipitations() {
            return precipitations;
        }

        public void setPrecipitations(int precipitations) {
            this.precipitations = precipitations;
        }
    }



