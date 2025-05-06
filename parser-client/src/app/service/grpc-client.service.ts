import { Injectable } from '@angular/core';
import * as grpcWeb from 'grpc-web';
import {XmlToJsonParserServiceClient} from '../proto/ParserServiceClientPb';
import {ParseRequest, ParseResponse} from '../proto/parser_pb';

@Injectable({
  providedIn: 'root'
})
export class GrpcClientService {
  private client: XmlToJsonParserServiceClient;

  constructor() {
    this.client = new XmlToJsonParserServiceClient('http://192.168.0.36:8080', null, null);
  }

  parseXml(xml: string): Promise<string> {
    const request = new ParseRequest();
    request.setXml(xml);

    return new Promise((resolve, reject) => {
      this.client.parse(request, {}, (err: grpcWeb.RpcError, response: ParseResponse) => {
        if (err) {
          reject(err);
        } else {
          resolve(response.getJson());
        }
      });
    });
  }
}
