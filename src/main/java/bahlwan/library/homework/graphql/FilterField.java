package bahlwan.library.homework.graphql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class FilterField {
    private String operator;
    private String value;

    public FilterField() {
    }

    public FilterField(String operator, String value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Predicate generateCriteria(CriteriaBuilder builder, Path field) {
        try {
            int v = Integer.parseInt(value);
            switch (operator) {
                case "lt": return builder.lt(field, v);
                case "le": return builder.le(field, v);
                case "gt": return builder.gt(field, v);
                case "ge": return builder.ge(field, v);
                case "eq": return builder.equal(field, value);
            }
        } catch (NumberFormatException e) {
            switch (operator) {
                case "endsWith": return builder.like(field, "%" + value);
                case "startsWith": return builder.like(field, value + "%");
                case "contains": return builder.like(field, "%" + value + "%");
            }
        }
        return builder.equal(field, value);
    }
}