import { environment as config } from './../../../../environments/environment';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent implements OnInit {
  // tslint:disable-next-line:ban-types
  tinymceInit: Object;
  apiKey = config.tiny_api_key;
  constructor() { }

  ngOnInit() {
    this.initTinymce();
  }

  initTinymce() {
    this.tinymceInit = {
      base_url: '/tinymce', // Root for resources
      suffix: '.min',       // Suffix to use when loading resources
      plugins: [
        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen',
        'insertdatetime media nonbreaking save table contextmenu directionality',
        'emoticons template paste textcolor colorpicker textpattern'
      ],
      toolbar1:
      // tslint:disable-next-line:max-line-length
        'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
      toolbar2: 'print preview media | forecolor backcolor emoticons',
      image_advtab: true,
      height: '500px',
      file_picker_callback: function (callback, value, meta) {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.onchange = function () {
          const file = input.files[0];
          const maxSizeByte = 2 * 1048576;
          if (file.size < maxSizeByte) {
            const reader = new FileReader();
            reader.onload = function (e) {
              callback(e.target['result'], {
                alt: ''
              });
            };
            reader.readAsDataURL(file);
          } else {
            alert('Please Choose A File Have Less Than 2MB');
          }
        };

        input.click();
      }
    };
  }


}
