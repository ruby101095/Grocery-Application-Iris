package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.Discount;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountEngine
{
    public BigDecimal calculate(
            Discount discount,
            int quantity,
            BigDecimal price) {

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.setVariable("quantity", quantity);
        context.setVariable("price", price);

        discount.getParams().forEach(context::setVariable);

        Object value = parser
                .parseExpression(discount.getExpression())
                .getValue(context);

        return new BigDecimal(value.toString());
    }
}
