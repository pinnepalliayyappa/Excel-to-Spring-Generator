import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EntityGenerateService {

  private entityGenerateUrl =  'http://localhost:8080/template/project';
  constructor(
    private http : HttpClient
  ) { }

  generateProject(request:any){

    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.post<string> (this.entityGenerateUrl,request,{headers});

  }
}
