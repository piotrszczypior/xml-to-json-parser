import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {AceModule} from 'ngx-ace-wrapper';
import format from 'xml-formatter';

@Component({
  selector: 'app-xml-editor',
  imports: [
    AceModule
  ],
  templateUrl: './xml-editor.component.html',
  styleUrl: './xml-editor.component.scss'
})
export class XmlEditorComponent {
  @ViewChild('textarea') textareaElement: ElementRef<HTMLTextAreaElement>;

  @Output()
  xmlChanged = new EventEmitter<string>();

  onChange(event: Event): void {
    const target = event.target as HTMLTextAreaElement;
    this.xmlChanged.emit(target.value);
  }

  formatXml(): void {
    const text = this.textareaElement.nativeElement.value;

    if (text) {
      try {
        this.textareaElement.nativeElement.value = format(text);
      } catch (e) {
        alert('Error by formatting XML: Syntax error');
      }
    }
  }

  clearTextarea(): void {
    this.textareaElement.nativeElement.value = '';
  }
}
