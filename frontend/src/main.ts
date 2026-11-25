import { Component } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root', standalone: true, imports: [FormsModule],
  template: `
  <header><span class="eyebrow">INSURANCE · PRIVACY CENTRE</span><h1>Your consent, your choice.</h1>
  <p>Review how your information may be used. You can change your choices at any time.</p></header>
  <main>
    <section class="panel"><h2>Consent preferences</h2>
      @for (item of purposes; track item.key) {
        <article><div><h3>{{item.title}}</h3><p>{{item.description}}</p></div>
          <label class="switch"><input type="checkbox" [(ngModel)]="item.enabled" [attr.aria-label]="item.title"><span></span></label>
        </article>
      }
      <button (click)="save()">Save my choices</button><p aria-live="polite">{{message}}</p>
    </section>
    <section class="panel"><h2>Privacy requests</h2><p>Get a copy of your data or request anonymisation of your profile.</p>
      <button class="secondary" (click)="message='Export request prepared (demo only).'">Request data export</button>
      <button class="secondary" (click)="message='Anonymisation request prepared (demo only).'">Request deletion</button>
    </section>
    <section class="panel"><h2>Consent history</h2><p>Changes are recorded with a timestamp, actor and reason in the audit trail.</p>
      <p class="muted">Connect the API to view your recorded history.</p>
    </section>
  </main>`
})
class AppComponent {
  message = '';
  purposes = [
    {key:'MARKETING', title:'Marketing communications', description:'Receive relevant product and service updates.', enabled:false},
    {key:'PARTNER_DATA_SHARING', title:'Data sharing with partners', description:'Allow sharing with selected service partners.', enabled:false},
    {key:'PROFILING', title:'Profiling', description:'Use information to tailor products and experiences.', enabled:false}
  ];
  save() { this.message = 'Preferences saved in this portal demo. API persistence is not yet wired.'; }
}
bootstrapApplication(AppComponent).catch(err => console.error(err));
