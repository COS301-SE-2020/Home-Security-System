import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { SideNavComponent } from './side-nav.component';

describe('SideNavComponent', () => {
  let component: SideNavComponent;
  let fixture: ComponentFixture<SideNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SideNavComponent ],
      imports: [HttpClientModule, RouterModule.forRoot([])]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SideNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  /*it('should display the side-nav options correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(SideNavComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#dashLink').textContent).toContain('Dashboard');
    expect(compiled.querySelector('#userLink').textContent).toContain('User List');
    expect(compiled.querySelector('#clearedLink').textContent).toContain('Cleared List');
    expect(compiled.querySelector('#unknownLink').textContent).toContain('Unknown List');
    expect(compiled.querySelector('#threatLink').textContent).toContain('Threat List');
  });*/
});
