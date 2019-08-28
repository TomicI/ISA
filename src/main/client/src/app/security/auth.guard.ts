import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {TokenService} from "../auth/token.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private route: ActivatedRoute,private router:Router,private tokenService:TokenService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    let snapshot = this.route.snapshot;

    const token = this.tokenService.getToken();
    if (token != null) {
      const authorities = this.tokenService.getAuthorities();
      for (let role of next.data.roles){
        if (authorities.includes(role)){
          console.log(role);
          return true;
        }
      }

    }

    // if (this.authService.){
    //   console.log("NAVIGACIJA")
    //   this.router.navigate(['sign_in']);
    //   return false;
    // }
    //
    // this.authService.roles().subscribe(data=>{
    //   console.log(data);
    //
    //   if (data.authorities.indexOf("ROLE_ADMIN")==-1) {
    //     console.log("NO RIGHTS");
    //
    //     return false;
    //   }
    //
    // });

    window.location.href='/login';
    return false;
  }
}
