import { Component, OnInit } from '@angular/core';
import { OpportunityService } from '../opportunity.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-trading-panel',
  templateUrl: './trading-panel.component.html',
  styleUrls: ['./trading-panel.component.css']
})
export class TradingPanelComponent implements OnInit {

  opportunity = {};
  opportunities = [];

  constructor(
    private opportutinyService: OpportunityService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.show();
  }

  show() {
    this.opportutinyService.list()
      .subscribe(response => this.opportunities = <any> response)
  }

  newOpp() {
    this.opportutinyService.add(this.opportunity)
      .subscribe(() => {
        this.opportunity = {};
        this.show();
        this.messageService.add({
          severity: 'success',
          summary:  'The Opportunity was added in the database'
        })
      },
      errorResponse => {

        let msg = 'Unexpected Error';

        if(errorResponse.error.message) {
          msg = errorResponse.error.message;
        }

        this.messageService.add({
          severity: 'error',
          summary:  msg
        })
      });
  }

  deleteOpp(index: any) {
    // console.log(index)
    this.opportutinyService.delete(index)
      .subscribe(() => {
        this.opportunity = {};
        this.show();
        this.messageService.add({
          severity: 'success',
          summary:  'The Opportunity was removed from the database'
        })
      },
      errorResponse => {

        let msg = 'Unexpected Error';

        if(errorResponse.error.message) {
          msg = errorResponse.error.message;
        }

        this.messageService.add({
          severity: 'error',
          summary:  msg
        })
      });
  }

}
