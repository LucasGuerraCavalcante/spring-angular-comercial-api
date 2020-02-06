import { Component, OnInit } from '@angular/core';
import { OpportunityService } from '../opportunity.service';

@Component({
  selector: 'app-trading-panel',
  templateUrl: './trading-panel.component.html',
  styleUrls: ['./trading-panel.component.css']
})
export class TradingPanelComponent implements OnInit {

  opportunities = [];

  constructor(private opportutinyService: OpportunityService) { }

  ngOnInit() {
    this.opportutinyService.list()
      .subscribe(response => this.opportunities = <any> response)
  }

}
