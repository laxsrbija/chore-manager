import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Overview} from "../model/dto/overview";

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) {
  }

  getOverview() {
    const loginHeaders = {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest',
          'Authorization': 'Basic Y2hhcmxlc0BleGFtcGxlLmNvbTpkZW1v',
        }
      )
    };

    return this.http.get<Overview>('/api/overview', loginHeaders);
  }
}
