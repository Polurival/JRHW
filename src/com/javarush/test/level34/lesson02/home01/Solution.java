package com.javarush.test.level34.lesson02.home01;

import java.util.ArrayList;
import java.util.List;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation)
    {
        //нахождение позиций открывающих и закрывающих скобок подвыражения
        boolean hasOpenBracketPos = false;
        int openBracketPos = 0;
        int closeBracketPos = 0;
        for (int i = 0; i < expression.toCharArray().length; i++)
        {
            char c = expression.charAt(i);
            if (c == '(' && hasOpenBracketPos)
            {
                openBracketPos = i;
            } else if (c == '(')
            {
                openBracketPos = i;
                hasOpenBracketPos = true;
            }
            if (c == ')')
            {
                closeBracketPos = i;
                break;
            }
        }

        // вычисление подвыражения:
        String subExpression;
        if (openBracketPos == 0 && closeBracketPos == 0)
        {
            subExpression = expression;
        } else
        {
            subExpression = expression.substring(openBracketPos + 1, closeBracketPos);
        }
        subExpression = subExpression.replaceAll(" ", "");

        //Создание списков операций и чисел
        List<String> operations = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        String number = "";
        boolean isOperationFirst = false;
        for (int i = 0; i < subExpression.toCharArray().length; i++)
        {
            char c = subExpression.charAt(i);
            if ((c >= '0' && c <= '9') || (c == '.'))
            {
                if (i < subExpression.toCharArray().length - 1)
                {
                    number += c;
                    continue;
                } else
                {
                    number += c;
                }
            }
            if (!number.equals(""))
            {
                numbers.add(number);
                number = "";
            }
            if (c == '-' || c == '+' || c == '*' || c == '/' || c == '^')
            {
                if (i == 0)
                {
                    isOperationFirst = true;
                }
                operations.add(String.valueOf(c));
            }
        }
        int operationsAmount = operations.size();

        //вычисление подвыражения:
        double subExpressionResult = 0d;
        while (operations.size() > 0)
        {
            //вычисление всех операций возведения в степень:
            while (operations.contains("^"))
            {
                //вычисление одной операции возведения в степень:
                int operationPos = 0;
                int numberForInvolutePos = 0;
                int involuteNumberPos = 0;
                double numberForInvolute;
                for (int i = 0; i < operations.size(); i++)
                {
                    if (operations.get(i).equals("^"))
                    {
                        operationPos = i;
                        if (isOperationFirst)
                        {
                            numberForInvolutePos = i - 1;
                            involuteNumberPos = i;
                            numberForInvolute = Double.valueOf(numbers.get(i - 1));
                            subExpressionResult = numberForInvolute;
                            for (int j = 1; j < Double.valueOf(numbers.get(i)); j++)
                            {
                                subExpressionResult = subExpressionResult * numberForInvolute;
                            }
                        } else
                        {
                            numberForInvolutePos = i;
                            involuteNumberPos = i + 1;
                            numberForInvolute = Double.valueOf(numbers.get(i));
                            subExpressionResult = numberForInvolute;
                            for (int j = 1; j < Double.valueOf(numbers.get(i + 1)); j++)
                            {
                                subExpressionResult = subExpressionResult * numberForInvolute;
                            }
                        }
                        break;
                    }
                }

                operations.remove(operationPos);
                numbers.remove(numberForInvolutePos);
                numbers.remove(involuteNumberPos - 1);

                if (isOperationFirst)
                {
                    numbers.add(operationPos - 1, String.valueOf(subExpressionResult));
                } else
                {
                    numbers.add(operationPos, String.valueOf(subExpressionResult));
                }
            }

            // вычисление всех операций умножения и деления:
            while (operations.contains("*") || operations.contains("/"))
            {
                //вычисление одной операции умножения или деления:
                int operationPos = 0;
                int firstNumberPos = 0;
                int secondNumberPos = 0;
                for (int i = 0; i < operations.size(); i++)
                {
                    String opChar = operations.get(i);
                    if (opChar.equals("*") || opChar.equals("/"))
                    {
                        operationPos = i;
                        if (isOperationFirst)
                        {
                            firstNumberPos = i - 1;
                            secondNumberPos = i;
                        } else
                        {
                            firstNumberPos = i;
                            secondNumberPos = i + 1;
                        }
                        double firstNumber = Double.valueOf(numbers.get(firstNumberPos));
                        double secondNumber = Double.valueOf(numbers.get(secondNumberPos));
                        if (opChar.equals("*"))
                        {
                            subExpressionResult = firstNumber * secondNumber;
                        } else if (opChar.equals("/"))
                        {
                            subExpressionResult = firstNumber / secondNumber;
                        }
                        break;
                    }
                }

                operations.remove(operationPos);
                numbers.remove(firstNumberPos);
                numbers.remove(secondNumberPos - 1);

                if (isOperationFirst)
                {
                    numbers.add(operationPos - 1, String.valueOf(subExpressionResult));
                } else
                {
                    numbers.add(operationPos, String.valueOf(subExpressionResult));
                }
            }

            // вычисление всех операций сложения и вычитания:
            subExpressionResult = 0d;
            for (int i = 0; i < operations.size(); i++)
            {
                if (isOperationFirst && (i == 0))
                {
                    subExpressionResult = Double.valueOf(operations.get(i) + numbers.get(i));
                    continue;
                } else if (!isOperationFirst && (i == 0))
                {
                    subExpressionResult = Double.valueOf(numbers.get(i));
                }

                String op = operations.get(i);
                switch (op)
                {
                    case "-":
                        if (isOperationFirst)
                        {
                            subExpressionResult -= Double.valueOf(numbers.get(i));
                        } else
                        {
                            subExpressionResult -= Double.valueOf(numbers.get(i + 1));
                        }
                        break;
                    case "+":
                        if (isOperationFirst)
                        {
                            subExpressionResult += Double.valueOf(numbers.get(i));
                        } else
                        {
                            subExpressionResult += Double.valueOf(numbers.get(i + 1));
                        }
                        break;
                }
            }
            operations.clear();
        }


        // после вычисления подвыражения, проверка и вычисление синуса, косинуса или тангенса:
        String trigoStr = null;
        if (openBracketPos >= 3)
        {
            String symbolsBeforeOpenBracket = expression.substring(openBracketPos - 3, openBracketPos);
            switch (symbolsBeforeOpenBracket)
            {
                case "sin":
                    subExpressionResult = Math.sin(Math.toRadians(subExpressionResult));
                    operationsAmount++;
                    trigoStr = "sin";
                    break;
                case "cos":
                    subExpressionResult = Math.cos(Math.toRadians(subExpressionResult));
                    operationsAmount++;
                    trigoStr = "cos";
                    break;
                case "tan":
                    subExpressionResult = Math.tan(Math.toRadians(subExpressionResult));
                    operationsAmount++;
                    trigoStr = "tan";
                    break;
                default:
                    break;
            }

            subExpressionResult = Math.rint(100 * subExpressionResult) / 100.0;
        }

        //Конкатенация нового expression для рекурсии:
        String newExpression;
        if (expression.contains("+") || expression.contains("-") || expression.contains("*") ||
                expression.contains("/") || expression.contains("^") || expression.contains("sin") ||
                expression.contains("cos") || expression.contains("tan"))
        {
            if ((trigoStr != null) && (expression.substring(openBracketPos - 3, openBracketPos).equals(trigoStr))) {
                newExpression = expression.substring(0, openBracketPos - 3) +
                        subExpressionResult +
                        (closeBracketPos == expression.length() - 1 ? "" : expression.substring(closeBracketPos + 1));
            } else
            {
                if (openBracketPos == 0 && closeBracketPos == 0)
                {
                    newExpression = String.valueOf(subExpressionResult);
                } else
                {
                    newExpression = expression.substring(0, openBracketPos) +
                            subExpressionResult +
                            (closeBracketPos == expression.length() - 1 ? "" : expression.substring(closeBracketPos + 1));
                }
            }
            recursion(newExpression, countOperation + operationsAmount);
        } else
        {
            System.out.println(expression + " " + (countOperation + operationsAmount));
        }
        //implement
    }

    public Solution()
    {
        //don't delete
    }
}
