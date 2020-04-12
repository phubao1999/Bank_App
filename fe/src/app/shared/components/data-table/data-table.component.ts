import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit, OnChanges } from '@angular/core';
import * as _ from 'lodash';
import { PagerService } from '../../services/helpers/pager.service';
declare var $: any;

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent implements OnInit, OnChanges {
  @Input() holder: string;
  @Input() listSearch: {}[];
  @Input() items: {}[];
  @Input() headers: {}[];
  @Input() isCheckItem = false;
  @Input() showSearch = true;
  @Input() showPage = true;
  @Input() showBtn = true;
  @Input() checkSearchFilter = false;
  @Input() isCheckItemRadio = false;
  @Input() pageSize = 20;
  @Input() checkDefault = [];
  @Output() outputAction = new EventEmitter();
  @Output() outputContentStatus = new EventEmitter();
  @Output() outputCheck = new EventEmitter();
  @Output() outputInput = new EventEmitter();
  @Output() outputDropdown = new EventEmitter();
  searchShow = false;
  allItems: {}[];
  searchItems: {}[];
  pager: any = {};
  pagedItems: {}[];
  listActiveFilter = [];
  isCheckAll = false;
  textSearchOld: string;
  checkDelete;
  constructor(private pagerService: PagerService) {
  }

  ngOnInit() {
  }

  ngOnChanges(change) {
    if (!this.showPage) {
      this.pageSize = 1000;
    }
    this.isCheckAll = false;
    // Header
    this.listActiveFilter = [];
    _.forEach(this.headers, header => {
      this.listActiveFilter.push({
        value: '',
        active: false
      });
    });

    // //Actions
    // let currentPath = window.location.pathname;
    // tslint:disable-next-line:max-line-length
    // if (this.items && this.items[0] && this.items[0]["actions"] && this.user && this.user.permissionList && this.user.permissionList[currentPath]) {

    //   let item = this.user.permissionList[currentPath].actions;
    //   let action = {}

    //   if (item.read) {
    //     action['View'] = true;
    //   }

    //   if (item.update) {
    //     action['Edit'] = true;
    //     action['Change Pass'] = true;
    //   }

    //   if (item.create) {
    //     action['Copy'] = true;
    //   }
    //   this.checkDelete = false;
    //   if (item.delete) {
    //     this.checkDelete = true;
    //     action['Delete'] = true;
    //   }

    //   let validActions = _.intersection(_.keys(action), this.items[0]["actions"]);
    //   _.map(this.items, (value, key) => {
    //     let validActions = _.intersection(_.keys(action), value["actions"]);
    //     value['actions'] = validActions;
    //   })

    //   if (validActions.lenght == 0) {
    //     if (this.headers[this.headers.length - 1] == 'Action') {
    //       this.headers = this.headers.slice(0, this.headers.length - 1);
    //     }
    //   }


    // PAGING
    this.allItems = this.items;
    if (this.listActiveFilter.length !== 0) {
      // tslint:disable-next-line:no-string-literal
      this.textSearch(this.textSearchOld, this.pager['currentPage'] === 0 ? 1 : this.pager['currentPage']);
      return;
    }
    this.searchItems = this.items;
    this.setPage(1);
  }

  clickContentStatus(item, clickAble, index) {
    if (clickAble) {
      this.outputContentStatus.emit({
        // tslint:disable-next-line:object-literal-shorthand
        item: item,
        // tslint:disable-next-line:object-literal-shorthand
        index: index
      });
    }
  }

  clickAction(action, item, index) {
    // tslint:disable-next-line:object-literal-shorthand
    this.outputAction.emit({ action: action, item: item, index: index });
  }

  setPage(page) {
    const self = this;
    if (!this.items || page < 1 || (this.pager && this.pager.totalPages !== 0 && page > this.pager.totalPages)) {
      return;
    }
    // get pager object from service
    page = (this.items.length / this.pageSize) < page ? Math.ceil(this.items.length / this.pageSize) : page;
    this.pager = this.pagerService.getPager(this.items.length, page, this.pageSize);
    // get current page of items
    this.pagedItems = this.items.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }

  filter() {
    $('.tr-filter').fadeToggle(300);
  }

  searchField(index) {
    this.searchShow = true;
    this.listActiveFilter[index].active = true;
  }

  closeSearch(index) {
    this.searchShow = false;
    this.listActiveFilter[index].active = false;
    this.listActiveFilter[index].value = '';
    this.textFilter(1);
  }

  choosepage(page: number) {
    this.setPage(page);
  }

  textSearch(event, page) {
    const arr = [];
    const self = this;
    this.textSearchOld = event;
    if (event && event !== '') {
      _.each(this.allItems, (item: any) => {
        _.forEach(self.listSearch, (search) => {
          if (_.toLower(item.content[search].title).indexOf(_.toLower(event)) > -1) {
            arr.push(item);
            return false;
          }
        });
      });
      this.searchItems = arr;
    } else {
      this.searchItems = this.allItems;
    }
    this.textFilter(page);
  }

  textFilter(page) {
    const self = this;
    // tslint:disable-next-line:only-arrow-functions
    const tmp = _.filter(this.searchItems, function(o) {
      let check = false;
      _.forEach(self.listActiveFilter, (filter, index) => {
        check = !filter.value || _.toLower(o.content[index].title).indexOf(_.toLower(filter.value)) > -1;
        if (!check) {
          return false;
        }
      });
      return check;
    });
    this.items = tmp;
    this.setPage(page);
  }

  onChange(event, value) {
    const check = event.currentTarget.checked;
    if (value === 'all') {
      this.isCheckAll = check;
      this.updateSelect(check);
    } else {
      let isAllCheck = true;
      if (this.isCheckItemRadio) {
        this.updateSelect(false);
      }
      _.forEach(this.pagedItems, (item, key) => {
        if (key === value) {
          // tslint:disable-next-line:no-string-literal
          item['check'] = check;
        }
        // tslint:disable-next-line:no-string-literal
        if (!item['check']) {
          isAllCheck = false;
        }
      });
      this.isCheckAll = isAllCheck;
    }
    // tslint:disable-next-line:only-arrow-functions
    const tmp = _.filter(this.pagedItems, function(o) {
      return o.check;
    });
    this.outputCheck.emit({ value: tmp });
  }

  updateSelect(check) {
    _.forEach(this.allItems, (item, key) => {
      item.check = check;
    });
  }

  searchFilter(value, index) {
    this.listActiveFilter[index].value = value;
    this.textFilter(1);
  }

  inputValue(value, item, index, j) {
    this.outputInput.emit({
      value,
      item,
      index,
      col: j
    });
  }

  dropDownSelect(value, item, index) {
    this.outputDropdown.emit({
      value,
      item,
      index
    });
  }
}
