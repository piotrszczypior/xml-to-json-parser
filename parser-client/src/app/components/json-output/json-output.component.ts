import {Component, Input} from '@angular/core';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-json-output',
  imports: [],
  templateUrl: './json-output.component.html',
  styleUrl: './json-output.component.scss'
})
export class JsonOutputComponent {
  @Input()
  jsonString: string;

  copyToClipboard(): void {
    try {
      if (navigator.clipboard) {
        navigator.clipboard.writeText(this.jsonString)
          .then(_ => () => {});
      } else {
        console.error('Browser does not support copying to clipboard');
      }
    } catch (err) {
      console.error('Copy failed:', err);
    }
  }
}
