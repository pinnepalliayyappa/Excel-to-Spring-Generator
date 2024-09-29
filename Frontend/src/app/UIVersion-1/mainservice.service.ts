import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MainserviceService {
  apiUrl = 'http://localhost:8080/template/dependencies';

  private projectData : any = null;
  constructor(
    private http: HttpClient
  ) {}
  getdropdownoptions():Observable<any>{
    const headers = new HttpHeaders({
      'Accept': 'application/json, text/plain, */*',
      'Accept-Encoding': 'gzip, deflate, br, zstd',
      'Accept-Language': 'en-GB,en-US;q=0.9,en;q=0.8,hi;q=0.7',
      'Origin': 'http://localhost:4200',
      'Sec-Fetch-Dest': 'empty',
      'Sec-Fetch-Mode': 'cors',
      'Sec-Fetch-Site': 'cross-site',
      'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36',
    });
    return this.http.get<any>(this.apiUrl);
  }

  // Method to set project data
  setProjectData(data: any) {
    this.projectData = data;
  }

  // Method to get project data
  getProjectData() {
    return this.projectData;
  }
}
