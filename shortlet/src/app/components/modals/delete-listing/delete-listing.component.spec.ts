import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteListingComponent } from './delete-listing.component';

describe('DeleteListingComponent', () => {
  let component: DeleteListingComponent;
  let fixture: ComponentFixture<DeleteListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteListingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
