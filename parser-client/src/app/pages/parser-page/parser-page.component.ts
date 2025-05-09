import {Component} from '@angular/core';
import {XmlEditorComponent} from '../../components/xml-editor/xml-editor.component';
import {JsonOutputComponent} from '../../components/json-output/json-output.component';
import {ConfirmButtonComponent} from '../../components/confirm-button/confirm-button.component';
import {GrpcClientService} from '../../service/grpc-client.service';
import {AsyncPipe, NgIf} from '@angular/common';

@Component({
  selector: 'app-parser-page',
  imports: [
    XmlEditorComponent,
    JsonOutputComponent,
    ConfirmButtonComponent,
    AsyncPipe,
    NgIf
  ],
  templateUrl: './parser-page.component.html',
  styleUrl: './parser-page.component.scss'
})
export class ParserPageComponent {
  xml: string = '';
  jsonOutput$: Promise<string> = Promise.resolve(' ');

  constructor(private readonly grpcService: GrpcClientService) {}

  changed($event: string) {
    this.xml = $event;
  }

  sendRequest(): void {
    if (this.xml !== '') {
      this.jsonOutput$ = this.grpcService.parseXml(this.xml);
    }
  }
}
