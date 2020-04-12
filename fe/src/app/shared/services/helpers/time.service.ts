import { Injectable } from '@angular/core';
import * as moment from 'moment';
import * as _ from 'lodash';

@Injectable()
export class TimeService {

  DATE_TIME_FORMAT = 'DD/MM/YYYY HH:mm';
  DATE_TIME_IMPORT_FORMAT = 'D/M/YYYY H:m';
  DATE_TIME_SS_FORMAT = 'DD/MM/YYYY HH:mm:ss';
  DATE_FORMAT = 'DD/MM/YYYY';
  DATE_TIME_FORMAT_REPORT = 'MM月DD日HH:mm';
  DATE_TIME_FORMAT_RETURN = 'YYYY年MM月';
  DATE_IMPORT_FORMAT = 'D/M/YYYY';
  YEAR_MONTH_DATE_FORMAT = 'YYYY-MM-DD';
  DATE_TIME_SS_FORMAT_CONCAT = 'YYYYMMDDHHmmss';
  MIN_TIME = '1/1/1700';
  MAX_TIME = '1/1/50000';
  DATE_TIME_FORMAT_JAPAN = 'YYYY/MM/DD';

  constructor() { }

  getTimeUtcFromTime(time, format) {
    return moment(time, format).utc();
  }

  getTimeFromTimeFormat(time, fomart) {
    return moment(time, fomart);
  }

  getTimeFormatFromTime(date, format) {
    return date ? moment(date).format(format) : '';
  }

  getTimeStampFromDate(date) {
    return moment(date).unix();
  }

  getTimeStampFromDateFormat(date) {
    return this.getTimeFromTimeFormat(date, this.DATE_TIME_FORMAT).unix();
  }

  compareTwoDate(date1, date2) {
    return this.getTimeStampFromDateFormat(date1) - this.getTimeStampFromDateFormat(date2);
  }

  getTimeUctCurrent() {
    return moment.utc();
  }

  getTimeCurrent(format) {
    return moment(new Date()).format(format);
  }

  convertFormatToFormat(time, format1, format2) {
    return this.getTimeFormatFromTime(this.getTimeFromTimeFormat(time, format1), format2);
  }

  addTime(time, dateAdd, format) {
    const result = this.getTimeFromTimeFormat(time, format);
    result.add(dateAdd, 'd');
    return this.getTimeFormatFromTime(result, format);
  }

  getTimeMomentCurrent() {
    return moment(new Date());
  }

  getDayBetweenTwoDate(date1, date2) {
    if (!date1 || !date2) {
      return 0;
    }
    return Math.floor((date2.getTime() - date1.getTime()) / 86400000);
  }

  formatDateFromTimeUnix(val, timeFormat) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return '';
    } else {
      const d = new Date(val * 1000);
      return moment(d).format(timeFormat);
    }
  }

  getTimeUnixCurrentByFormat(format) {
    const time = moment().format(format);
    return this.getTimeUnixFromTimeFormat(time);
  }

  compareTwoDatesToGetDay(date1, date2) {
    if (date1 && date2) {
      const date1Unix = moment.unix(date1);
      date1Unix.hour(0);
      date1Unix.minute(0);
      date1Unix.second(0);

      const date2Unix = moment.unix(date2);
      date2Unix.hour(0);
      date2Unix.minute(0);
      date2Unix.second(0);
      return date1Unix.unix() - date2Unix.unix();
    }
    return 0;
  }

  getTimeUnixFromTimeFormat(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment(val, this.DATE_TIME_SS_FORMAT).unix();
    }
  }
  getTimeUnixFromTimeFormatUTC(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment.utc(val, this.DATE_TIME_SS_FORMAT).unix();
    }
  }
  getTimeUnixFromTimeFormatYMD(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment(val, this.YEAR_MONTH_DATE_FORMAT).unix();
    }
  }
  getTimeUnixFromTimeFormatYMDHMS(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment(val, this.DATE_TIME_SS_FORMAT_CONCAT).unix();
    }
  }

  getTimeUnixFromTimeFormatJapan(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment(val, this.DATE_TIME_FORMAT_JAPAN).unix();
    }
  }

  getTimeUnixFromDate(val) {
    if (val === null || val === '' || _.isUndefined(val)) {
      return null;
    } else {
      return moment(val, this.YEAR_MONTH_DATE_FORMAT).unix();
    }
  }

  getTimeUnixCurrent() {
    return moment().unix();
  }

  validateDateTime(time, format, startTime) {
    const wrongFormat = !moment(time, format, true).isValid();
    if (wrongFormat) {
      return `Invalid date format, please key in date in format ${format}`;
    } else if (startTime && this.compareTwoDate(startTime, time) > 0) {
      return 'Invalid Date';
    } else {
      return null;
    }
  }

  validateDate(time, format) {
    const wrongFormat = !moment(time, format, true).isValid();
    if (wrongFormat) {
      return `Invalid date format, please key in date in format ${format}`;
    } else {
      return null;
    }
  }

  getDateAndTimeByTimeUnix(time) { // cover TimeUnix to year, month, day, hour, minutes
    const date = moment(time * 1000).toDate();
    // tslint:disable-next-line:max-line-length
    const abc = { year: date.getFullYear(), month: date.getMonth() + 1, day: date.getDate(), hour: date.getHours(), minutes: date.getMinutes() };
    return abc;
  }

  daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
  }
}
