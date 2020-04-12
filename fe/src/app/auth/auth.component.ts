import { AuthService } from './auth.service';
import { ValidateService } from './../shared/services/helpers/validate.service';
import { Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit, OnDestroy {
  formLogin: FormGroup;
  formRegister: FormGroup;
  formErr = {
    idUser: '',
    password: ''
  };
  validateMess = {
    idUser: {
      required: 'Please Enter Your Id',
      pattern: 'Id Must Be A Number'
    },
    password: {
      required: 'Please Enter Your Password'
    }
  };
  formResErr = {
    name: '',
    password: '',
    phoneNumber: '',
    dayOfBirth: '',
    idBank: ''
  };
  validateResMess = {
    name: {
      required: 'Please Enter Your Name',
      pattern: 'Name Do not have number',
    },
    password: {
      required: 'Please Enter Your Password'
    },
    phoneNumber: {
      required: 'Please Enter Your Phone Number'
    },
    dayOfBirth: {
      required: 'Please Enter Day Of Birth'
    },
    idBank: {
      required: 'Please Enter Choose Your Bank'
    }
  };
  isLogin = true;
  loginData;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private validateService: ValidateService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.initFormRegister();
  }

  ngOnDestroy() {
    // this.loginData.unSubcribe();
  }

  initForm() {
    this.formLogin = this.formBuilder.group({
      idUser: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      password: ['', [Validators.required]]
    });
    this.formLogin.valueChanges.subscribe(() => {
      console.log(this.formResErr);
      this.validateService.getValidate(
        this.formLogin, this.formErr, this.validateMess
      );
    });
  }

  initFormRegister() {
    this.formRegister = this.formBuilder.group({
      // tslint:disable-next-line:max-line-length
      name: ['', [Validators.required, Validators.pattern('[A-Z][a-zA-Z][^#&<>\"~;$^%{}?]{1,20}$')]],
      password: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      dayOfBirth: ['', [Validators.required]],
      idBank: ['', [Validators.required]]
    });
    this.formRegister.valueChanges.subscribe(() => {
      this.validateService.getValidate(
        this.formRegister, this.formResErr, this.validateResMess
      );
    });
  }

  changeField() {
    this.isLogin = !this.isLogin;
  }

  login() {
    const body = {
      id: this.formLogin.value.idUser,
      password: this.formLogin.value.password
    };
    this.authService.login(body).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }

  register() {
    const body = {
      name: this.formRegister.value.name,
      sdt: this.formRegister.value.phoneNumber,
      password: this.formRegister.value.password,
      day_of_birth: this.formRegister.value.dayOfBirth,
      id_bank: this.formRegister.value.idBank,
      monney: 0
    };
    this.authService.register(body).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }

}
