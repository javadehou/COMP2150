
/**
 * Write a description of class IPConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;


public class InfixPostfixConverter 
{
    
    public static class SyntaxErrorException extends Exception
    {
        SyntaxErrorException(String message)
        {
            super(message);
        }
        
    }
    
    //Data fields
    private Stack<Character> operatorStack;
    
    //The operators
    private static final String OPERATORS = "+-*/()";
    
    //The precedence of the operators, matches order in OPERATORS
    private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
    
    //The postfix string
    private StringBuilder postfix;
    
    
    
    //Convert a string from infix to postfix
    public String convert(String infix) throws SyntaxErrorException
    {
        operatorStack = new Stack<Character>();
        postfix = new StringBuilder();
        
        StringTokenizer infixTokens = new StringTokenizer(infix);
        try
        {
            //Proces each token in the infix string
            while (infixTokens.hasMoreTokens())
            {
                String nextToken = infixTokens.nextToken();
                char firstChar = nextToken.charAt(0);
                
                //Is it an operand?
                if (Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar))
                {
                    postfix.append(nextToken);
                    postfix.append(' ');
                }
                
                //is is an operator?
                else if (isOperator(firstChar))
                {
                    processOperator(firstChar);
                }
                else
                {
                    throw new SyntaxErrorException ("Unexpected Character Encountered: " + firstChar);
                }
                
            }
            
            //Pop any remaining operators and append them to postfix
            while (!operatorStack.empty())
            {
                char op = operatorStack.pop();
                //Any 'y' on the stack is not matched
                if (op == ')')
                {
                    throw new SyntaxErrorException("unmatched opening parenthesis");
                }
                postfix.append(op);
                postfix.append(' ');                
                
            }
            
            //assert: Stack is empty, return result
            return postfix.toString();
            
        }
        catch(EmptyStackException ex)
        {
            throw new SyntaxErrorException("Syntax Error: The stack is empty");
        }
        
        
    }
    
    private void processOperator(char op)
    {
        if (operatorStack.empty() || op == '(')
        {
            operatorStack.push(op);
        }
        else
        {
            //Peek the operator stack and let topoOp be top operator
            char topOp = operatorStack.peek();
            if (precedence(op) > precedence(topOp))
            {
                operatorStack.push(op);
            }
            else
            {
                //pop all stacked operators with equal or higher precedence than op
                while (!operatorStack.empty() && precedence(op) <= precedence(topOp))
                {
                    operatorStack.pop();
                    if (topOp == '(')
                    {
                        break;
                    }
                    
                    postfix.append(topOp);
                    postfix.append(' ');
                    
                    if (!operatorStack.empty())
                    {
                        //reset topOp.
                        topOp = operatorStack.peek();
                    }
                }       
                if (op != ')')
                {
                    operatorStack.push(op);
                }
            
            }
            
        }
        
        
    
    }
    
    //Determine whether a character is an operator
    private boolean isOperator(char ch)
    {
        return OPERATORS.indexOf(ch) != -1;
    }
    
    //determine the precedence of an operator
    private int precedence(char op)
    {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
    
}
