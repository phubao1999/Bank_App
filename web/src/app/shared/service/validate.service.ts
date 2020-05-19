import { Injectable } from '@angular/core';
import { FormControl } from '@angular/forms';

@Injectable()
export class ValidateService {

  constructor() { }

  public getValidate(form, formErrors, validationMessages) {
    if (!form) {
      return formErrors;
    }
    for (const field in formErrors) {
      // clear previous error message (if any)
      formErrors[field] = '';
      const control = form.get(field);
      if (control && control.dirty && !control.valid && !formErrors[field]) {
        const messages = validationMessages[field];
        // tslint:disable-next-line:forin
        for (const key in control.errors) {
          if (!formErrors[field] && messages[key]) {
          formErrors[field] += messages[key] + '';
          }
        }
      }
    }
    return formErrors;
  }

  public matchOtherValidator(otherControlName: string) {

    let thisControl: FormControl;
    let otherControl: FormControl;

    return function matchOtherValidate(control: FormControl) {

      if (!control.parent) {
        return null;
      }
      if (!control.value) {
        return null;
      }

      if (!thisControl) {
        thisControl = control;
        otherControl = control.parent.get(otherControlName) as FormControl;
        if (!otherControl) {
          throw new Error('matchOtherValidator(): other control is not found in parent group');
        }
        otherControl.valueChanges.subscribe(() => {
          thisControl.updateValueAndValidity();
        });
      }

      if (!otherControl) {
        return null;
      }

      if (otherControl.value !== thisControl.value) {
        return {
          matchOther: true
        };
      }
      return null;
    };

  }

// no contetn validater
public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { whitespace: true };
  }

}
