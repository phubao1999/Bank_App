import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import * as _ from 'lodash';
@Component({
  selector: 'app-select-dropdown',
  templateUrl: './select-dropdown.component.html',
  styleUrls: ['./select-dropdown.component.scss']
})
export class SelectDropdownComponent implements OnInit {
  @Input() selectedIndex: number = -1;
  @Input() options: {}[];
  @Input() title: string;
  @Input() nameSelect: string;
  @Input() isSearch: boolean;
  @Output() selectedOption = new EventEmitter();
  textShow;
  optionShows: {}[];
  textSearch;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(change) {
    this.creatOption()
  }

  creatOption() {
    let self = this;
    this.optionShows = [];
    this.textShow = null;
    let datas = _.filter(this.options, (option) => {
      return !this.textSearch || _.toLower(option.itemName).indexOf(_.toLower(this.textSearch)) > -1
    })

    _.forEach(datas, function (option, index) {
      let check = option.itemName == self.nameSelect || index == self.selectedIndex;
      let item = { text: option.itemName&&option.itemName.length > 40 ? option.itemName.substr(0, 60) + '...' : option.itemName, value: option.itemName, check: check };
      if (check) {
        self.textShow = item.text;
      }
      self.optionShows.push(item)
    })

    this.textShow = this.textShow ? this.textShow : this.nameSelect?this.nameSelect:this.title;
  }

  handleSelectOption(value) {
    this.textShow = value;
    let item = _.findLast(this.options, function (o) {
      return o.itemName == value;
    });
    if (!item) {
      item = {
        itemName: null,
        id: undefined
      }
    }
    this.selectedOption.emit({ value: item })
  }

  searchName(text) {
    this.textSearch = text;
    this.creatOption();
  }

}
