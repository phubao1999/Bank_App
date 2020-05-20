import { ComponentActions } from './../../shared/component/component-actions';
import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { ValidateService } from './../../shared/service/validate.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  formLoginError = {
    email: '',
    password: ''
  };
  errFormLoginMess = {
    email: {
      required: 'PLease Enter This Field',
      pattern: 'This field Is must be a email like: abc@gmail.com'
    },
    password: {
      required: 'Please Enter This Field'
    }
  };
  constructor(
    private formBuilder: FormBuilder,
    private validateService: ValidateService,
    private authService: AuthService,
    private router: Router,
    private componentActions: ComponentActions
  ) { }

  ngOnInit(): void {
    this.createFormLogin();
  }

  createFormLogin() {
    this.formLogin = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern('^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$')]],
      password: ['', [Validators.required]]
    });
    this.formLogin.valueChanges.subscribe(() => {
      this.validateService.getValidate(
        this.formLogin, this.formLoginError, this.errFormLoginMess
      );
    });
  }

  login() {
    this.componentActions.showLoading();
    const body = {
      email: this.formLogin.value.email,
      password: this.formLogin.value.password
    };
    this.authService.login(body).subscribe(res => {
      const token = res['data']['token'];
      const info = res['data']['user'];
      localStorage.setItem('token', token);
      sessionStorage.setItem('user-info', JSON.stringify(info));
      const message = res['meta']['message'];
      alert(message);
      this.router.navigate(['dashboard']);
      this.componentActions.hideLoading();
    }, err => {
      const message = err['error']['meta']['message'];
      alert(message);
      this.componentActions.hideLoading();
    });
  }

}
