package io.github.emfsilva.restfull.controllers;

import io.github.emfsilva.restfull.converters.NumberConverter;
import io.github.emfsilva.restfull.math.SimpleMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private NumberConverter numberConverter;
    private SimpleMath math;

    @Autowired
    public MathController(NumberConverter numberConverter, SimpleMath math) {
        this.numberConverter = numberConverter;
        this.math = math;
    }


    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return math.sum(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return math.subtraction(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return math.multiplication(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return math.division(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return math.mean(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/square/{number}", method = RequestMethod.GET)
    public Double square(@PathVariable("number") String number) {
        if (!numberConverter.isNumeric(number)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }
        return math.squareRoot(numberConverter.convertToDouble(number));

    }
}
