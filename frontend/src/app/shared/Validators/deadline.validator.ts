import { AbstractControl } from "@angular/forms";

export function ValidateDeadline(control: AbstractControl) {
    let deadline : string = control.value;
    if (deadline.length == 0 || deadline.replace(/\D/g,'').length < 8) {
      return { invalidDeadline: true };
    }
    return null;
}    