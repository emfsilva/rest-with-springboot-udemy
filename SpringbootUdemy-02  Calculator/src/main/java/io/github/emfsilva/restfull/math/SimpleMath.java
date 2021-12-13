package io.github.emfsilva.restfull.math;

public class SimpleMath {

    public Double sum(Double firtNumber, Double secondNumber) {
        return firtNumber + secondNumber;
    }
    public Double subtraction(Double firtNumber, Double secondNumber) {
        return firtNumber - secondNumber;
    }
    public Double multiplication(Double firtNumber, Double secondNumber) {
        return firtNumber * secondNumber;
    }
    public Double division(Double firtNumber, Double secondNumber) {
        return firtNumber / secondNumber;
    }
    public Double mean(Double firtNumber, Double secondNumber) {
        return (firtNumber + secondNumber) / 2;
    }
    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

    public String remnant(Double firtNumber, Double secondNumber) {
        Double result =  firtNumber % secondNumber;
        if(result == 0) {
            return "ODD";
        } else {
            return "EVEN";
        }
    }

}
