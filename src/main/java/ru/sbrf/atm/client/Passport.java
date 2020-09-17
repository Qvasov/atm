package ru.sbrf.atm.client;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
public class Passport {
	@NotNull
	@Digits(integer = 4, fraction = 0)
	@Size(min = 4, max = 4)
	String series;
	@NotNull
	@Digits(integer = 4, fraction = 0)
	@Size(min = 6, max = 6)
	String number;
}
