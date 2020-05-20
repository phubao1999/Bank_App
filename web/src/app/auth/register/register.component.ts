import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidateService } from './../../shared/service/validate.service';
import { AuthService } from './../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  idUser: number;
  formRegister: FormGroup;
  formOtp: FormGroup;
  formStep = 1;
  formRegisterErrors = {
    name: '',
    email: '',
    password: ''
  };
  errRegisterMess = {
    name: {
      required: 'You Must enter this field'
    },
    email: {
      required: 'You Must enter this field',
      pattern: 'This Field Must Be An Email Like: abc@gmail.com'
    },
    password: {
      required: 'You Must Enter Your Password',
      pattern: 'Password Have 1 uppercase charracter, 1 special symbol and number'
    }
  };
  formOtpErrors = {
    otp: ''
  };
  errFormOtp = {
    otp: {
      required: 'You Must Enter Your Otp Code',
      pattern: 'Must Be A Number'
    }
  };
  constructor(
    private formBuilder: FormBuilder,
    private validateService: ValidateService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.createFormRegister();
    this.createFromOtp();
  }

  createFormRegister() {
    this.formRegister = this.formBuilder.group({
      name: ['', [Validators.required]],
      // tslint:disable-next-line:max-line-length
      email: ['', [Validators.required, Validators.pattern('^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$')]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,8}$')]]
    });
    this.formRegister.valueChanges.subscribe(() => {
      this.validateService.getValidate(
        this.formRegister, this.formRegisterErrors, this.errRegisterMess
      );
    });
  }

  createFromOtp() {
    this.formOtp = this.formBuilder.group({
      otp: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
    });
    this.formOtp.valueChanges.subscribe(() => {
      this.validateService.getValidate(
        this.formOtp, this.formOtpErrors, this.errFormOtp
      );
    });
  }

  async registerNGenOtp() {
    await this.createUser()
    .then(val => {
      this.genOtp(val);
    })
    .catch(err => {
      console.log(err);
    });
  }

  genOtp(res) {
    this.idUser = res.data.idUser;
    const body = {
      email: res.data.email,
      id_user: res.data.idUser
    };
    this.authService.createOtp(body).subscribe(val => {
      // tslint:disable-next-line:no-string-literal
      const mess = val['meta']['message'];
      alert(mess);
      this.formStep = 2;
    }, err => {
      console.log(err);
    });
  }

  createUser() {
    return new Promise((resolve, rejects) => {
      const body = {
        name: this.formRegister.value.name,
        email: this.formRegister.value.email,
        password: this.formRegister.value.password
      };
      this.authService.register(body).subscribe(res => {
        resolve(res);
      }, err => {
        rejects(err);
      });
    });
  }

  checkOtp() {
    const body = {
      id_user: this.idUser,
      otp: this.formOtp.value.otp
    };
    this.authService.activeUser(body).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }

}
