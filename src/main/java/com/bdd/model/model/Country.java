package com.bdd.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Comparable {

    /* ID - ISO 3166 */
    @NotEmpty
    String code;

    @Override
    public int compareTo(Object object) {
        Country country = (Country) object;
        return code.compareTo(country.getCode());
    }


}
