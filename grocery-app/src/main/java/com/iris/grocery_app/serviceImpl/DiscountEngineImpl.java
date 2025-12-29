package com.iris.grocery_app.serviceImpl;

import com.iris.grocery_app.entity.Discount;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountEngineImpl {

    private final ExpressionParser parser = new SpelExpressionParser();

    public BigDecimal calculateItemLevel(
            Discount discount,
            int quantity,
            BigDecimal price) {

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("quantity", quantity);
        context.setVariable("price", price);

        discount.getParams().forEach(context::setVariable);

        return evaluate(discount, context);
    }

    public BigDecimal calculateCartLevel(
            Discount discount,
            BigDecimal subtotal) {

        if (discount == null) {
            return BigDecimal.ZERO;
        }

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("subtotal", subtotal);

        discount.getParams().forEach(context::setVariable);

        return evaluate(discount, context);
    }

    private BigDecimal evaluate(
            Discount discount,
            StandardEvaluationContext context) {

        Object value = parser
                .parseExpression(discount.getExpression())
                .getValue(context);

        return value == null
                ? BigDecimal.ZERO
                : new BigDecimal(value.toString());
    }
}
