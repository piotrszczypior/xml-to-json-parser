import {Component, Input} from '@angular/core';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-json-output',
  imports: [
    NgClass
  ],
  templateUrl: './json-output.component.html',
  styleUrl: './json-output.component.scss'
})
export class JsonOutputComponent {
  @Input() jsonString: string = '{\n' +
    '    "root": {\n' +
    '        "animals": [\n' +
    '            {\n' +
    '                "cat": "miau",\n' +
    '                "dog": {\n' +
    '                    "name": "Maja"\n' +
    '                },\n' +
    '                "@text": "ggwp, testt"\n' +
    '            },\n' +
    '            {\n' +
    '                "cat": [\n' +
    '                    "miau",\n' +
    '                    "miau2"\n' +
    '                ],\n' +
    '                "dog": {\n' +
    '                    "name": "Rex"\n' +
    '                },\n' +
    '                "@text": "testt"\n' +
    '            }\n' +
    '        ],\n' +
    '        "dell": {\n' +
    '            "cat": [\n' +
    '                "miau",\n' +
    '                "miau2"\n' +
    '            ],\n' +
    '            "dog": {\n' +
    '                "name": "Rex"\n' +
    '            },\n' +
    '            "@text": "testt"\n' +
    '        },\n' +
    '        "@text": "aaa"\n' +
    '    }\n' +
    '}';

  formattedJson: string = '';
  hasError: boolean = false;

  // ngOnChanges(changes: SimpleChanges): void {
  //   if (changes['jsonString']) {
  //     this.formatJson();
  //   }
  // }

  private formatJson(): void {
    if (!this.jsonString) {
      this.formattedJson = '';
      return;
    }

    try {
      // Parse the JSON to ensure it's valid
      const parsedJson = JSON.parse(this.jsonString);

      // Format with 2 spaces indentation
      const prettyJson = JSON.stringify(parsedJson, null, 2);

      // Apply syntax highlighting
      this.formattedJson = this.highlightJson(prettyJson);
      this.hasError = false;
    } catch (error) {
      // If parsing fails, display the original string as an error
      this.formattedJson = this.jsonString;
      this.hasError = true;
    }
  }

  private highlightJson(json: string): string {
    // Escape HTML to prevent XSS
    let escaped = this.escapeHtml(json);

    // Replace patterns with spans for highlighting
    return escaped
      // Strings
      .replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, (match) => {
        let cls = 'number';

        if (/^"/.test(match)) {
          if (/:$/.test(match)) {
            cls = 'key';
            // Remove the colon from the match and add it separately
            match = match.replace(/:$/, '');
            return `<span class="${cls}">${match}</span><span class="punctuation">:</span>`;
          } else {
            cls = 'string';
          }
        } else if (/true|false/.test(match)) {
          cls = 'boolean';
        } else if (/null/.test(match)) {
          cls = 'null';
        }

        return `<span class="${cls}">${match}</span>`;
      })
      // Add highlighting for brackets and punctuation
      .replace(/[{}\[\],]/g, (match) => {
        return `<span class="punctuation">${match}</span>`;
      });
  }

  private escapeHtml(text: string): string {
    const map: { [key: string]: string } = {
      '&': '&amp;',
      '<': '&lt;',
      '>': '&gt;',
      '"': '&quot;',
      "'": '&#039;'
    };

    return text.replace(/[&<>"']/g, (m) => map[m]);
  }
}
