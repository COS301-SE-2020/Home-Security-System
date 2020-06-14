import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LiveFeedComponent } from './live-feed.component';

describe('LiveFeedComponent', () => {
  let component: LiveFeedComponent;
  let fixture: ComponentFixture<LiveFeedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiveFeedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiveFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    fixture = TestBed.createComponent(LiveFeedComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  /*
  it(`should have as title 'argus-app'`, () => {
    fixture = TestBed.createComponent(LiveFeedComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('argus-app');
  });
  */
/*
  it('should render title', () => {
    fixture = TestBed.createComponent(LiveFeedComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('argus-app app is running!');
  });
  */
});
