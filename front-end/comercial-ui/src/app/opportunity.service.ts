import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class OpportunityService {

  apiUrl = 'http://localhost:8080/opportunities';

  constructor(private httpClient: HttpClient) { 

  }

  list() {
    return this.httpClient.get(this.apiUrl);
  }

  add(opportunity: any) {
    return this.httpClient.post(this.apiUrl, opportunity);
  }

  delete(index: any) {
    return this.httpClient.delete(this.apiUrl + `/${ index }`);
  }
} 
