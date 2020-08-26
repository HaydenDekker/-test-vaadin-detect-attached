import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/vaadin-text-area.js';
import '@vaadin/vaadin-button/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/vaadin-vertical-layout.js';

class FormView extends PolymerElement {
  static get template() {
    return html`
      <custom-style>
        <style include="shared-styles lumo-typography">
          #form-view {
            display: block;
            padding: 1rem;
          }

          :host {
            display: block;
            padding: 1rem;
          }

          .button-layout {
            display: flex;
            flex-wrap: wrap-reverse;
            width: 100%;
            justify-content: flex-end;
          }
        </style>
      </custom-style>

      <vaadin-vertical-layout id="wrapper" theme="padding">
        <h1>Form</h1>
        <vaadin-form-layout>
          <vaadin-form-item>
            <label slot="label">First name</label>
            <vaadin-text-field id="firstName" class="full-width" value=""></vaadin-text-field>
          </vaadin-form-item>
          <vaadin-form-item>
            <label slot="label">Last name</label>
            <vaadin-text-field id="lastName" class="full-width" value=""></vaadin-text-field>
          </vaadin-form-item>
          <vaadin-form-item colspan="2">
            <label slot="label">Email</label>
            <vaadin-text-field id="email" class="full-width" value=""></vaadin-text-field>
          </vaadin-form-item>
        </vaadin-form-layout>
        <vaadin-horizontal-layout
          theme="spacing"
          style="display:flex;flex-wrap:wrap-reverse;width:100%;justify-content:flex-end;"
        >
          <vaadin-button theme="tertiary" id="cancel" slot="">
            Cancel
          </vaadin-button>
          <vaadin-button theme="primary" id="save">
            Save
          </vaadin-button>
        </vaadin-horizontal-layout>
      </vaadin-vertical-layout>
    `;
  }

  static get is() {
    return 'form-view';
  }

  static get properties() {
    return {
      // Declare your properties here.
    };
  }
}

customElements.define(FormView.is, FormView);
