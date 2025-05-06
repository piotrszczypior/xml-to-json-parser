import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-confirm-button',
  imports: [],
  templateUrl: './confirm-button.component.html',
  styleUrl: './confirm-button.component.scss'
})
export class ConfirmButtonComponent {
  @Input()
  text: string;

  @Output()
  confirm = new EventEmitter();

  onClick(event: Event) {
    event.stopPropagation();
    event.preventDefault();
    this.confirm.emit();
  }
}
