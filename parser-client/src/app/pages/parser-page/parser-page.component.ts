import { Component } from '@angular/core';
import {XmlEditorComponent} from '../../components/xml-editor/xml-editor.component';
import {JsonOutputComponent} from '../../components/json-output/json-output.component';
import {ConfirmButtonComponent} from '../../components/confirm-button/confirm-button.component';
import {GrpcClientService} from '../../service/grpc-client.service';

@Component({
  selector: 'app-parser-page',
  imports: [
    XmlEditorComponent,
    JsonOutputComponent,
    ConfirmButtonComponent
  ],
  templateUrl: './parser-page.component.html',
  styleUrl: './parser-page.component.scss'
})
export class ParserPageComponent {
  xml: string = '';

constructor(private readonly grpcService: GrpcClientService) {}
  changed($event: string) {
    this.xml = $event;
  }

  sendRequest() {
    this.grpcService.parseXml('<root>text</root>').then(r => console.log(r));
  }
}
