import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from "./client"

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private baseURL = "localhost:8080/v1/clients";
  constructor(private httpClient : HttpClient) { }

  getClientsList() : Observable<Client[]> {
    return this.httpClient.get<Client[]>('${this.baseURL')
  } 
}
