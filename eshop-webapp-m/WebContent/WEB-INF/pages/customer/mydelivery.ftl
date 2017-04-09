<!DOCTYPE HTML>
<html>
<head>
	<title>我的地址</title>
	<meta name="keywords" content="我的地址"/> 
	<meta name="description" content="我的地址"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	
	<style>
		* {
			margin: 0;
			padding: 0;
			-webkit-tap-highlight-color: rgba(0,0,0,0)
		}
		body {
			margin: 0;
			padding: 0;
			color: #3d4245
		}
		ul, li, ol, p {
			list-style: none;
			font-size: 14px;
			color: #3d4245
		}
		h4, h5, em {
			font-style: normal;
			font-weight: 400
		}
		a {
			text-decoration: none;
			color: #3d4245
		}
		.clear {
			clear: both
		}
		.order-buy {
		}
		.order-buy .address {
			color: #3d4245;
			padding: 12px;
			padding-left: 40px;
			padding-right: 24px;
			background: #fff;
			position: relative;
			margin-bottom: 12px;
			background:url(https://pic2.lowol.cn/upfiles/activity/lovo/M/fgx.png) center bottom repeat-X;
		}
		.order-buy .address>div {
			padding: 4px 0
		}
		.order-buy .address::before {
			content: '';
		    display: inline-block;
		    width: 14px;
		    height: 14px;
		    position: absolute;
		    left: 14px;
		    bottom: 16px;
		    background: url(../../images_v2/address.png) no-repeat;
		    background-size: 14px;
		    background-position: 0 0;
		}
		.order-buy .address::after {
			content: '';
			display: inline-block;
			width: 10px;
			height: 10px;
			position: absolute;
			right: 12px;
			top: 40%;
			border-right: 1px solid #bfbfbf;
			border-top: 1px solid #bfbfbf;
			-webkit-transform: rotate(45deg)
		}
		.order-buy .address .address-info {
			clear:both; width:100%; display:inline-block; font-weight:bolder
		}
		.order-buy .address .address-info p {
			float:left;
			font-size: 14px;
			color: #3d4245;
			overflow: hidden;
			word-break: break-all
		}
		.order-buy .address .address-info .address-phone { padding-left:5px;
		}
		.order-buy .address .address-details {
			font-size: 12px;
			line-height: 16px
		}
		.order-buy .address .address-vice-explain {
			font-size: 10px;
			color: rgba(255,255,255,.5)
		}
		.order-buy .order-info {
			background: #fff;
			margin-top: 12px;
			padding: 0 12px;
			border-bottom: 1px solid #e0e0e0
		}
		.order-buy .order-info .seller-name {
			font-size: 14px;
			padding: 16px 0;
			border-bottom: 1px solid #e0e0e0
		}
		.order-buy .order-info .order-list-info li {
			position: relative
		}
		.order-buy .order-info .order-list-info .list-info {
			display: -webkit-box;
			padding: 8px 0;
			border-bottom: 1px solid #e0e0e0
		}
		.order-buy .order-info .order-list-info .list-info .list-cont {
			-webkit-box-flex: 1
		}
		.order-buy .order-info .order-list-info .list-info .list-img {
			display: inline-block;
			width: 80px;
			margin-right: 8px;
			text-align: center
		}
		.order-buy .order-info .order-list-info .list-info .list-img a {
			max-height: 80px;
			line-height: 80px;
			display: block
		}
		.order-buy .order-info .order-list-info .list-info .list-img a img {
			max-width: 80px;
			max-height: 80px
		}
		.order-buy .order-info .order-list-info .list-info .list-img p img {
			width: 100%
		}
		.order-buy .order-info .order-list-info .list-price-nums {
			margin:0;
			width:100%;
		height: 20px;
			line-height: 20px;
		
		}
		.order-buy .order-info .order-list-info .list-price-nums p {
		
		}
		.order-buy .order-info .order-list-info .list-price-nums .nums {
			line-height:1rem;
		}
		.order-buy .order-info .order-list-info .list-cont .goods-title {
			font-size: 12px;
			overflow: hidden;
			height:32px;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
			display: -webkit-box;
			line-height: 16px
		}
		.order-buy .order-info .order-list-info .list-cont .goods-title img {
			width: 18px
		}
		.order-buy .order-info .order-list-info .list-cont .godds-specification {
			font-size: 12px;
			color: #999;
			height:32px;
			line-height:16px;
			overflow:hidden;
		}
		.order-buy .order-info .goods-preferential {
			font-size: 12px;
			color: #999;
			border-bottom: 1px solid #ddd;
			padding: 8px 0
		}
		.order-buy .order-info .buy-nums {
			position: relative
		}
		.order-buy .order-info .buy-nums .nums {
			width: 120px;
			position: absolute;
			right: 0;
			top: 7px;
			display: inline-block;
			border: 1px solid #c5c8cf;
			text-align: left;
			overflow: hidden
		}
		.order-buy .order-info .buy-nums .minus, .order-buy .order-info .buy-nums .plus, .order-buy .order-info .buy-nums .input-nums {
			float: left
		}
		.order-buy .order-info .buy-nums .minus, .order-buy .order-info .buy-nums .plus {
			width: 32px;
			height: 32px;
			line-height: 32px;
			text-align: center;
			color: #6e758a;
			font-size: 18px
		}
		.order-buy .order-info .buy-nums .minus.lock, .order-buy .order-info .buy-nums .plus.lock {
			color: #d5d5d5
		}
		.order-buy .order-info .buy-nums .minus {
			border-right: 1px solid #c5c8cf
		}
		.order-buy .order-info .buy-nums .plus {
			border-left: 1px solid #c5c8cf
		}
		.order-buy .order-info .buy-nums .input-nums {
			width: 53px;
			height: 32px;
			text-align: center
		}
		.order-buy .order-info .subtotal {
			padding: 12px 0;
			text-align: right;
			font-size: 12px;
			border-top: 1px solid #eee;
			margin-top: -1px
		}
		.order-buy .order-info .subtotal .total-text {
			color: #999
		}
		.order-buy .order-info .subtotal .price {
			font-size: 16px;
			font-weight: 700
		}
		.order-buy .order-group {
			background: #fff
		}
		.order-buy .order-group .seller-name {
			font-size: 14px;
			padding: 16px 0;
			border-bottom: 1px solid #e0e0e0;
			margin: 0 12px
		}
		.order-buy .order-group .promotion {
			margin: 0 12px
		}
		.order-buy .order-group .subtotal {
			padding: 12px 0;
			text-align: right;
			font-size: 12px
		}
		.order-buy .order-group .subtotal .price {
			font-size: 16px;
			font-weight: 700
		}
		.order-buy .order-group .subtotal .total-text {
			color: #999
		}
		.order-buy .order-group .order-info {
			margin-bottom: 0
		}
		.order-buy .order-group .subtotal {
			padding-right: 12px
		}
		.order-buy .order-bond {
			background: #fff;
			margin-bottom: 10px
		}
		.order-buy .order-bond .seller-name {
			font-size: 14px;
			padding: 16px 0;
			border-bottom: 1px solid #ddd;
			margin: 0 12px
		}
		.order-buy .order-bond .promotion {
			margin: 0 12px
		}
		.order-buy .order-bond .subtotal {
			padding: 12px 0;
			text-align: right;
			font-size: 12px
		}
		.order-buy .order-bond .subtotal .price {
			font-size: 16px;
			font-weight: 700
		}
		.order-buy .order-bond .subtotal .total-text {
			color: #999
		}
		.order-buy .order-bond .order-info {
			margin-top: 0
		}
		.order-buy .order-bond .subtotal {
			padding-right: 12px
		}
		.order-buy .order .b-box {
			padding: 16px 0
		}
		.order-buy .b-box {
			display: -webkit-box;
			position: relative;
			padding: 16px 12px;
			border-bottom: 1px solid #e0e0e0
		}
		.order-buy .b-box .title {
			display: inline-block
		}
		.order-buy .b-box .interpret {
			-webkit-box-flex: 1;
			width: 100%;
			text-align: right;
			font-size: 12px;
			color: #999;
			padding-left: 10px
		}
		.order-buy .b-box.arrow, .order-buy .b-box .arrow {
			padding-right: 24px
		}
		.order-buy .b-box.arrow::after, .order-buy .b-box .arrow::after {
			content: '';
			display: inline-block;
			width: 8px;
			height: 8px;
			position: absolute;
			right: 8px;
			top: 40%;
			border-right: 1px solid #bfbfbf;
			border-top: 1px solid #bfbfbf;
			-webkit-transform: rotate(45deg)
		}
		.order-buy .b-box .multi-select>div {
			position: relative;
			min-height: 20px
		}
		.order-buy .b-box .multi-select .arrow span {
			line-height: 20px
		}
		.order-buy .b-box .multi-select .arrow .sitem-tip{
			float: left;padding: 0em .6em;background: #f23030;color: #fff;font-size: .8em;font-style: normal;
		}
		.order-buy .b-box .multi-select hr {
			border: 0;
			height: 1px;
			background: #eee;
			margin: 16px 0
		}
		.order-buy .b-box .multi-select .c-order-select {
			position: absolute;
			right: 30px;
			top: 0;
			height: 100%;
			display:none;
		}
		.order-buy .b-box .multi-select .c-order-select span{color:#000}
		.order-buy .bridge {
			padding-left: 12px;
			padding-right: 12px;
			background: #fff
		}
		.order-buy .order-inputs {
			width: 100%
		}
		.order-buy .order-inputs p:last-child {
			padding-bottom: 16px;
			border-bottom: 1px solid #e0e0e0
		}
		.order-buy .order-inputs p {
			padding-top: 16px
		}
		.order-buy .inputs {
			padding: 12px 0
		}
		.order-buy .anyChannel {
			padding: 0 12px;
			background: #fff
		}
		.order-buy .options {
			padding: 12px
		}
		.order-buy .options .vice-t {
			font-size: 10px;
			color: #999
		}
		.order-buy .options .title {
			-webkit-box-flex: 1;
			display: -webkit-box;
			width: 100%;
			-webkit-box-align: center
		}
		.order-buy .options .interpret {
			-webkit-box-flex: none;
			width: auto;
			display: inline-block
		}
		.order-buy .table {
			width: 100%
		}
		.order-buy .table p {
			padding-bottom: 10px;
			margin-bottom: 10px;
			border-bottom: 1px solid #e0e0e0;
			line-height: 18px
		}
		.order-buy .table p span {
			margin-right: 10px;
			word-break: break-all
		}
		.order-buy .table p:last-child {
			margin: 0;
			padding: 0;
			border: 0
		}
		.order-buy .terms {
			background: #fff
		}
		.order-buy .terms .title {
			padding-right: 30px;
			background: url(../../images_v2/lian.png) no-repeat right 5px;
			background-size: 24px
		}
		.order-buy .terms.d {
			padding-right: 0;
			padding-left: 0
		}
		.order-buy .bottom-bar{position: fixed;
		    bottom: 0; width:100%}
		.order-buy .bottom-bar .total-price {
			bottom: 0;
			left: 0;
			height:54px;
			width: 100%;
			background: #fff;
			border-top: 1px solid #ddd;
			color: #c40001;
			font-size: 12px;
			text-align: right;
			display: -webkit-box;
			-webkit-box-align: center
		}
		.order-buy .bottom-bar .total-price>div {
			width: 100%
		}
		.order-buy .bottom-bar .total-price p {
			display: inline-block;
			height:54px;
			color: #c40001;
			float: right;
			overflow: hidden
		}
		.order-buy .bottom-bar .total-price .realPay {
			line-height: 54px;
			margin-right: 16px
		}
		.order-buy .bottom-bar .total-price .price {
			font-size: 16px;
			font-weight: 700
		}
		.order-buy .c-order-btn {
			border: 0;
			display: inline-block;
			text-align: center;
			text-decoration: none;
			width:100px;
			height: 54px;
			line-height:54px;
			color: #fff;
			font-size: 16px;
			background: #c40001
		}
		.order-buy .c-order-btn-disabled {
			background: #dedede;
			color: #4e4e4e
		}
		.order-buy .c-inputs {
			background: #ededed;
			-webkit-border-radius: 4px;
			display: inline-block;
			width: 100%;
			height: 30px;
			padding: 0 8px;
			box-sizing: border-box
		}
		.order-buy .c-order-checkbox {
			width: 52px;
			height: 34px;
			background: url(../../images_v2/xuanze.png) no-repeat 0 0;
			background-size: contain
		}
		.order-buy .c-order-checkbox:checked {
			background: url(../../images_v2/xuanze2.png) no-repeat 0 0;
			background-size: 52px
		}
		.order-buy .c-order-select {
			background: 0 0;
			height: 18px;
			line-height: 18px;
			text-align: right;
			width: 100%
		}
		.order-buy .c-order-select:checked {
			text-align: right
		}
		.order-buy .mask {
			display: none;
			width: 100%;
			height: 100%;
			position: absolute;
			z-index: 5;
			left: 0;
			top: 0;
			bottom: 0;
			-webkit-transform: translate3d(0px, 0, 0);
			background: url(../../images_v2/loading-grey.gif) 50% 50% no-repeat no-repeat fixed rgba(0,0,0,.34902)
		}
		html, body {
			margin: 0;
			padding: 0;
			width: 100%;
			height: 100%
		}
		ul, ol {
			-webkit-margin-before: 0;
			-webkit-margin-after: 0;
			-webkit-margin-start: 0;
			-webkit-margin-end: 0;
			-webkit-padding-before: 0;
			-webkit-padding-after: 0;
			-webkit-padding-start: 0;
			-webkit-padding-end: 0
		}
		body * {
			-webkit-text-size-adjust: none;
			-webkit-tap-highlight-color: rgba(0,0,0,0)
		}
		.navbar {
			-webkit-box-sizing: border-box;
			position: relative;
			width: 100%;
			z-index: 99;
			top: 0;
			left: 0
		}
		.navbar>ul {
			position: relative;
			width: 100%;
			height: 100%;
			background: 0
		}
		.navbar>ul.forward {
			-webkit-transform: translate(50px, 0);
			opacity: 0
		}
		.navbar>ul.backward {
			-webkit-transform: translate(-50px, 0);
			opacity: 0
		}
		.navbar>ul.transition {
			-webkit-transition: .4s ease;
			-webkit-transition-property: -webkit-transform opacity;
			-webkit-transform: translate(0, 0);
			opacity: 1
		}
		.navbar:after {
			display: block;
			content: ' ';
			clear: both;
			visibility: hidden
		}
		.navbar>ul>li {
			position: absolute;
			height: 100%;
			display: -webkit-box;
			-webkit-box-pack: center;
			-webkit-box-align: center
		}
		.navbar>ul>li:first-child {
			width: 100%
		}
		.navbar>ul>li:nth-child(2) {
			left: 0;
			top: 0;
			padding: 0 10px
		}
		.navbar>ul>li:last-child {
			right: 0;
			top: 0;
			padding: 0 10px
		}
		.navbar a {
			top: 0;
			display: inline-block;
			text-align: center;
			position: relative;
			padding: 0 5px;
			margin: 2px
		}
		.toolbar {
			position: relative;
			z-index: 99
		}
		.viewport {
			width: 100%;
			min-height: 100%
		}
		.viewport>.navbar {
			display: none
		}
		.viewport>.content {
			overflow: auto;
			width: auto;
			height: auto
		}
		.viewport>.toolbar {
			display: none
		}
		.viewport>.content>.wrap, .viewport>.content>.wrap>.active, .viewport>.content>.wrap>.inactive {
			width: 100%;
			min-height: 100%
		}
		.viewport>.content>.wrap>.active {
			display: block
		}
		.viewport>.content>.wrap>.inactive {
			display: none
		}
		.viewport>.content>.forward, .viewport>.content>.backward {
		}
		.viewport>.content>.forward>.next, .viewport>.content>.backward>.prev {
		}
		.viewport>.content>.trans {
			display: none;
			background: transparent;
			position: absolute;
			width: 100%;
			height: 100%;
			left: 0;
			top: 0;
			overflow: hidden;
			z-index: 9999
		}
		.viewport>.content>.trans>div {
			position: absolute;
			width: 100%;
			height: 100%
		}
		.viewport.enableNavbar>.navbar {
			display: block
		}
		.viewport.enableToolbar>.toolbar {
			display: block
		}
		.viewport.enableScroll, .viewport.enableScroll>.content, .viewport.enableScroll>.content>.wrap {
			position: relative;
			overflow-y: hidden;
			height: 100%
		}
		.viewport.enableTransition, .viewport.enableTransition>.content, .viewport.enableTransition>.content>.wrap {
			position: relative;
			overflow-x: hidden;
			width: 100%
		}
		.c-footer {
			height: 98px;
			background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAABECAIAAADP4sgBAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2RpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo3OEIzODNFMEY3QzNFMTExOEYyQThEMkVFMTk5RkJBQSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoxQzc5MTA0REM0MEUxMUUxQTZGMTg1MTk2RDcyNDZBNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoxQzc5MTA0Q0M0MEUxMUUxQTZGMTg1MTk2RDcyNDZBNiIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M1IFdpbmRvd3MiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo3OUIzODNFMEY3QzNFMTExOEYyQThEMkVFMTk5RkJBQSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo3OEIzODNFMEY3QzNFMTExOEYyQThEMkVFMTk5RkJBQSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pn5VZNsAAAAoSURBVHjaYrx79y4TAwMDGDMyMsLZyBhdnBQ+NeTINYMYPpo4QIABAB8FA1JZ0MRmAAAAAElFTkSuQmCC) 0 30px repeat-x;
			font-size: 14px;
			text-align: center
		}
		.c-footer a {
			color: #047;
			text-decoration: none
		}
		.c-footer .c-footer-t {
			display: -webkit-box;
			padding: 0 8px 0 10px;
			height: 30px;
			line-height: 30px;
			background: -webkit-gradient(linear, left top, left bottom, from(#fefefe), to(#ededed))
		}
		.c-footer .c-footer-t .c-user-info {
			-webkit-box-flex: 1;
			display: -webkit-box;
			text-align: left
		}
		.c-footer .c-footer-t .c-user-info span:nth-child(2) {
			display: inline-block;
			text-indent: 12px
		}
		.c-footer .c-footer-t .c-gotop {
			width: 80px;
			text-align: right;
			font-size: 12px;
			font-family: verdana;
			font-weight: 700
		}
		.c-footer .c-footer-t .c-gotop a {
			color: #999;
			text-transform: capitalize;
			position: relative;
			padding-right: 15px;
			display: inline-block
		}
		.c-footer .c-footer-t .c-gotop a b {
			position: absolute;
			width: 0;
			height: 0;
			top: 10px;
			right: 0;
			border: 4px transparent solid;
			border-bottom-color: #999
		}
		.c-footer .c-footer-l {
			padding: 12px 0 9px
		}
		.c-footer .c-footer-l a {
			margin-left: 17px
		}
		.c-footer .c-footer-l a:first-child {
			margin-left: 0
		}
		.c-footer .c-copyright {
			color: #666;
			font-size: 12px
		}
		.c-footer .c-copyright a {
			color: #666
		}
		.c-footer .c-copyright .c-cr-sv {
			margin-left: 10px
		}
		.c-loading {
			width: 100%;
			min-height: 24px;
			background-size: 30px 12px;
			background: url(https://pic2.lowol.cn/upfiles/activity/lovo/M/loading.gif) center center no-repeat
		}
		.c-gray-loading {
			width: 100%;
			min-height: 24px;
			background-size: 20px 9px;
			background: url(https://pic2.lowol.cn/upfiles/activity/lovo/M/loading-grey.gif) center center no-repeat
		}
		
		/*V1 CSS*/
		.taitou {
			width:100px;
			height: 20px;
			margin-right:5px;
			color:#999;
			border: 1px solid #DDDDDD;
		}
		.innercontent {
			margin: 0px 15px;
			padding: 15px 0; 
		}
		.innercontent .listCoupon li {
			position:relative;
			width:300px;
			width:320px;
			margin:0 auto;
			boder:1px;
		}
		.innercontent .listCoupon li a {
			display:block;
			margin-bottom:20px;
		}
		.innercontent .listCoupon li a .l1 {
			display:-webkit-box;
			margin-bottom:6px;
		}
		.innercontent .listCoupon li a .l1 p {
			-webkit-box-flex:1;
			font-size:16px;
			padding:0 10px 0 0;
		}
		.innercontent .listCoupon li a .l2 {
			font-size:12px;
			line-height:16px;
			padding-right:25px;
		}
		.innercontent .listdz li {
			position:relative;
			width:300px;
		}
		.innercontent .listdz li a {
			display:block;
			margin-bottom:20px;
		}
		.innercontent .listdz li a .city {
			display:-webkit-box;
			margin-bottom:6px;
		}
		.innercontent .listdz li a .city p {
			-webkit-box-flex:1;
			font-size:16px;
			padding:0 10px 0 0;
		}
		.innercontent .listdz li a .city p:first-child {
			display:inline-block;
			font-weight:bold;
		}
		.innercontent .listdz li a .street {
			font-size:12px;
			line-height:16px;
			padding-right:25px;
		}
		.innercontent .listdz li .del {
			position:absolute;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background-color:rgba(95, 95, 95, 0.6);
		}
		.innercontent .listdz li .del .deltxt {
			position:absolute;
			right:20px;
			top:30%;
			color:white;
			font-weight:bold;
		}
		.innercontent .listdz li:first-child>a:after {
			content:url(../../images/gou.png);
			display:block;
			position:absolute;
			top:30%;
			right:4px;
		}
		.innercontent .detaildz li {
			display:-webkit-box;
			margin-bottom:8px;
		}
		
		.innercontent .detaildz li:nth-child(2) {
		margin-bottom:20px;
		}
		.innercontent .detaildz li .left {
			margin-left:5px;
			width:80px;
			height:30px;
			line-height:30px;
			font-size:14px;
			color:#333;
		}
		.innercontent .detaildz li .right {
			position:relative;  width:70%;
		}
		.innercontent .detaildz li .right input, .innercontent .detaildz li .right textarea {
			height:1.6em;
			width:100%;
			border:solid 1px #DDD;
			color:#333333;
				padding-left:9px;
			font-size:16px;
			-webkit-border-radius:0;
			-webkit-appearance:none;
			background:-webkit-gradient(linear, 0 0, 0 100%, from(#f5f5f5), to(#fdfdfd));
		}
		.innercontent .detaildz li .right input:focus, .innercontent .detaildz li .right textarea:focus {
			outline:none;
			background:#fff;
			-webkit-tap-highlight-color:rgba(255, 255, 255, 0);
		}
		.innercontent .detaildz li .right input:disabled, .innercontent .detaildz li .right textarea:disabled {
			background:#f5f5f5;
			border-color:#ccc;
			color:#ccc;
		}
		.innercontent .detaildz li .right input.error, .innercontent .detaildz li .right textarea.error {
			border-color:#EC0000;
			border-width:2px;
			width:190px;
			height:29px;
			padding-left:8px;
		}
		.innercontent .detaildz li .right input::-webkit-input-placeholder, .innercontent .detaildz li .right textarea::-webkit-input-placeholder {
		font-size:16px;
		color:#ccc;
		}
		.innercontent .detaildz li .right textarea {
			height:50px;
			border:solid 1px #DDD;
			padding-left:9px;
			color:#333333;
			font-size:16px;
			-webkit-border-radius:0;
			-webkit-appearance:none;
			background:-webkit-gradient(linear, 0 0, 0 100%, from(#f5f5f5), to(#fdfdfd));
		}
		.innercontent .detaildz li .right textarea:focus {
			outline:none;
			background:#fff;
			-webkit-tap-highlight-color:rgba(255, 255, 255, 0);
		}
		.innercontent .detaildz li .right textarea:disabled {
			background:#f5f5f5;
			border-color:#ccc;
			color:#ccc;
		}
		.innercontent .detaildz li .right textarea.error {
			border-color:#EC0000;
			border-width:2px;
			width:190px;
			height:48px;
			padding-left:8px;
		}
		.innercontent .detaildz li .right textarea::-webkit-input-placeholder {
		font-size:16px;
		color:#ccc;
		}
		.innercontent .detaildz li .right .c-form-select {
			position:relative;
			width:100%;
		}
		.innercontent .detaildz li .right .c-form-select:after {
			content:"";
			top:11px;
			right:7px;
			position:absolute;
			display:inline-block;
			font-size:0;
			border-top:2px solid #666666;
			border-left:2px solid #666666;
			width:6px;
			height:6px;
			background-color:transparent;
			-webkit-transform:rotate(225deg);
		}
		.innercontent .detaildz li .right .c-form-select select {
			-webkit-appearance:none;
			width:100%;
			height:1.6em;
			-webkit-border-radius:0;
			border:solid 1px #D5D5D5;
			border-width:1px;
			color:#333333;
			font-size:16px;
			padding-right:16px;
			background:#ffffff;
		}
		.innercontent .detaildz li .right .c-form-select select option {
			white-space:nowrap;
			text-overflow:ellipsis;
			overflow:hidden;
		}
		.innercontent .detaildz li .right .c-form-select select:focus {
			outline:none;
		}
		.innercontent .detaildz li .right .c-form-select select {
			text-indent:9px;
		}
		.innercontent .detaildz .asdefault {
			margin:20px 5px;
		}
		.innercontent .detaildz .c-btn-oran-big.disable {
			background:#666;
			-webkit-box-shadow:none;
		}
		.innercontent .nodz {
			position:relative;
			height:150px;
		}
		.innercontent .nodz .nocontainer {
			position:absolute;
			right:20px;
		}
		.innercontent .nodz .nocontainer .txtnote {
			float:left;
			margin-top:45px;
			width:110px;
		}
		
		.c-btn-oran {
			background:#c40001;
			border: 0 none;
			color: #FFFFFF;
			display: inline-block;
			font-size: 18px;  display:block; padding:5px;
			font-weight: bolder;
			height: 40px;
			line-height: 40px;
			padding: 0;
			text-align: center;
			text-decoration: none;
			max-width:90%;
		}
		
		/*--地址--*/
		.bdb-1px {
		    position: relative;
			  
		}
		.bdb-1px:after {
		    bottom: 0;
		    border-top: 1px solid #e0e0e0;
		    content: ' ';
		    display: block;
		    width: 100%;
		    position: absolute;
		    right: 0;
		}
		.address {
		    padding-bottom: 74px;
		}
		
		.item-addr {
		    padding: .7em 10px 0;
		    overflow: hidden;
			 padding-left: 15px;
		}
		.item-addr .addck{ height: 1em;
		    line-height: 1em;
		    text-align: center;
		    font-size: 0.8em;
			float:left;}
		.item-addr .ia-m { width:100%; display: inline-block; margin-bottom:.7em}
		.item-addr .ia-m .mt_new {
		    color: #222;
		}
		.item-addr .ia-m p {
		    font-size: 13px;
		    float: left;
		    color: #666;
		    font-size: .8125em;
		    line-height: 1.6em;
		    padding-top: .6em;
		}
		
		.item-addr .ia-m .sitem-tip {
		    margin: 0 1em .4em 0;
			 margin-right: 2px;
		}
		.sitem-tip {
		    position: relative;
		    top: -1px;
		    left: 0;
		}
		.sitem-tip {
		    padding: .2em .6em;
		    background: #c40001;
		    color: #fff;
		    font-size: .875em;
		    font-style: normal;
		}
		.item-addr .addck .moren { 
		    display: inline-block;
		    width: 1em;
		    height: 1em;
		    background: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/moren.png) no-repeat;
		    background-size: 1em;vertical-align: middle;
		}
		.item-addr .addck .on { 
		    display: inline-block;vertical-align: middle;
		    width: 1em;
		    height: 1em;
		    background: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/moren-on.png) no-repeat;
		    background-size: 1em;
		}
		.item-addr .ia-r .iar-icon { 
		    display: inline-block;
		    width: 1em;
		    height: 1em;
		    background: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/bianxie.png) no-repeat;
		    background-size: 1em;vertical-align: middle;
		}
		.item-addr .ia-r .cart-checkbox {
		   display: inline-block;
		    width: 1em;
		    height:1em;
		    background: url(https://pic2.lowol.cn/upfiles/activity/lovo/M/delet.png) no-repeat;
		    background-size: 1em;vertical-align: middle;
		}
		.item-addr .ia-r {
		padding: 0 0 0 16px;
		    display: inline-block;
		    height: 1em;
		    line-height: 1em;
		    text-align: center;
		    font-size: 0.8em;
			float:right;
		}
		.item-addr .adddel {
		    margin-top: -15px;
		    position: absolute;
		    right: 3%;
		    top: 20%;
			font-size:12px;
		    border-left: 1px solid #e3e3e3;
		    padding: 0 0 0 16px;
		    display: inline-block;
		    height: 1.875rem;
		    width:24px;
		    text-align: center;
		}
		.pay-btn  {
		    height: 70px;
		    line-height: 50px;
		    text-align: center;
		    background: #f0f2f5;
		    position: fixed;
		    left: 0;
		    width: 100%;
		    z-index: 6;
		    bottom: 0%;
		    margin: auto;}
			
		.btn1.tip-btn:link {
		    color: #fff;
		}
		.btn1.tip-btn {
		    font-size: 16px;
		    color: #fff;
		    width: 180px;
		    height: 44px;
		    line-height: 44px;
		    margin-top: 15px;
		    margin-bottom: 15px;
		}
		.btn1 {
		    width: 47.81%;
		    background: #c40001;
		    display: inline-block;
		    border-radius: 2px;
		    text-align: center;
		    font-size: 15px;
		}
	</style>
</head>
<body>
<#include "/global/global_back.ftl" />
<section id="listAddress" class="innercontent">
    <div class="address ">
    <ul>
    	<#if customerDeliveries??&&(customerDeliveries?size>0)>
    		<#list customerDeliveries as deliverie>
		    <li>
			    <div class="item-addr bdb-1px ">
			        <div class="ia-m">
			          <div class="mt_new">
			            <span>${deliverie?if_exists.consignee}</span>
			            <strong><#if (deliverie.mobile)??>${deliverie?if_exists.mobile}<#else>${deliverie?if_exists.tel}</#if></strong>
			             
			          </div>
			          <div class="mc">
			            <p>${deliverie?if_exists.province} ${deliverie?if_exists.city} ${deliverie?if_exists.district} ${deliverie?if_exists.address}</p>
			          </div>
			        </div>
			        <div class="ia-m">
			        <label class="addck"><input type="radio" name="RadioGroup1" value="单选" <#if (deliverie.is_default)??&&(deliverie.is_default==0)>onclick="setIsDefault(${deliverie?if_exists.id});"</#if>><span <#if (deliverie.is_default)??&&(deliverie.is_default==1)>class="on" <#else>class="moren" onclick="setIsDefault(${deliverie?if_exists.id});"</#if>></span> 设为默认</label>
			        <a href="#" onclick="deleteDelivery(${deliverie?if_exists.id})" class="ia-r">
			        <span class="cart-checkbox"></span> 删除</a>
			        <#--<a class="ia-r" href=""><span class="iar-icon"></span> 编辑</a>-->
			        </div>
			    </div>
		    </li>
		    </#list>
      	</#if>
    </ul>
  
    </div>
    <div class="pay-btn" style="background:#f8f8f8;">
      <a href="javascript:;" class="btn1 tip-btn " onclick="addDelivery();" report-eventid="MMyJD_AddressNew"><i class="plus">+</i>新建地址</a>
    </div>
</section>

<section id="newAddress" class="innercontent" style="display:none;">
	<div class="detaildz"> 
		<ul>
			<li>
				<div class="left">收货人</div>
				<div class="right">
				<div><input type="text" maxlength="30" autofocus="autofocus" value="" id="consignee"></div>
				</div>
			</li>
			<li>
				<div class="left">手机号码</div>
				<div class="right">
				<div><input type="tel" value="" required="" id="mobile" placeholder="固话、手机选填其一"></div>
				</div>
			</li>
			<li>
				<div class="left">固定电话</div>
				<div class="right">
				<div><input type="tel" value="" required="" id="tel" placeholder="固话、手机选填其一"></div>
				</div>
			</li>
			<li>
				<div class="left">省份</div> 
				<div class="right"> 
				<div class="c-form-select">
					<select id="province" name="selectedDelivery.province"><option value="0">请选择省</option><option value="25">上海</option><option value="2">北京</option><option value="6">广东</option><option value="16">江苏</option><option value="31">浙江</option><option value="22">山东</option><option value="23">山西</option><option value="24">陕西</option><option value="21">青海</option><option value="26">四川</option><option value="27">天津</option><option value="28">西藏</option><option value="29">新疆</option><option value="30">云南</option><option value="32">重庆</option><option value="33">香港</option><option value="34">澳门</option><option value="20">宁夏</option><option value="19">内蒙古</option><option value="3">安徽</option><option value="4">福建</option><option value="5">甘肃</option><option value="7">广西</option><option value="8">贵州</option><option value="9">海南</option><option value="10">河北</option><option value="11">河南</option><option value="12">黑龙江</option><option value="13">湖北</option><option value="14">湖南</option><option value="15">吉林</option><option value="17">江西</option><option value="18">辽宁</option><option value="35">台湾</option></select>
					 
				</div>
				</div>
			</li>
			<li>
				<div class="left">城市</div>
				<div class="right"> 
				<div class="c-form-select">
					<select id="city" name="selectedDelivery.city" style="display: none;">
					<option value="0">请选择</option> 
					</select>
				                    
				</div>
				</div>
			</li>
			<li>
				<div class="left">地区</div>
				<div class="right"> 
				<div class="c-form-select">
					<select id="county" name="selectedDelivery.county" style="display: none;">
					<option value="0">请选择</option>
					</select>
				          
				</div>
				</div>
			</li>
			<li>
				<div class="left">详细地址</div>
				<div class="right">
				<div><textarea maxlength="120" id="address"></textarea></div>
				</div>
			</li>
			<li>
			<!--用于区分新增还是修改-->
			<input type="hidden" id="modifyId" value="">
				<a class="c-btn-oran " href="javascript:addCustomerDelivery();" style="width:50%; margin-top:20px;"><span>确认</span></a>
				<a class="c-btn-oran " href="javascript:window.location.reload();" style="width:50%;background: #fff; border:1px solid #e0e0e0; color:#999; margin-top:20px"><span>取消</span></a>
			</li>
		</ul> 
				
	</div>
</section>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
<script type="text/javascript">
	function setIsDefault(delivery_id){
		$.ajax({
			url: "/customer/delivery/default.do",
			type: "post",
			data: {"delivery_id" : delivery_id},
			beforeSend:function(){
		    	$("#loading").show();
		    },
			success: function(data) {
				if (data.needlogin == true) {
					window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
					return ;
				}
				if (data.code == "200") {
					window.location.reload();
				} else {
					alert(data.msg);
				}
			},
			complete:function(){$("#loading").hide();}
		});
	}
	
	function deleteDelivery(delivery_id){
		if(!confirm("确认删除？？？")){
			return false;
		}
		$.ajax({
			url: "/customer/delivery/delete.do",
			type: "post",
			data: {"delivery_id" : delivery_id},
			beforeSend:function(){
		    	$("#loading").show();
		    },
			success: function(data) {
				if (data.needlogin == true) {
					window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
					return ;
				}
				if (data.code == "200") {
					window.location.reload();
				} else {
					alert(data.msg);
				}
			},
			complete:function(){$("#loading").hide();}
		});
	}
	
	function addDelivery(){
		$("#listAddress").hide();
		$("#newAddress").show();
	}

	$(document).ready(function() {
		AddressSelector.init(); 
	});
	
	var AddressSelector = { 
		init : function() { 
			$("#city,#county").hide();
			// 元素事件初始化
			$("#province").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						$("#city,#county").empty().hide();
						if (id == 0) {
							alert("请输入收货省份");
						}else{
						  AddressSelector.refresh('city');
						}
					});
			$("#city").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						$("#county").empty().hide();
						if (id == 0) {
							alert("请输入收货市/区");
						}else{
						  AddressSelector.refresh('county');
						}  
					});
			$("#county").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						if (id == 0) {
							alert("请输入收货区/县");
						}  
					});
			this.refresh('province');
		},
		refresh : function(type) { 
			if(!type){
				return;
			}
			var url = "/customer/delivery/area.do";
			var data=null; 
			if (type == 'city') {
				data = {parent_id:$("#province").val(), area_type:2}; 
			} else if (type == 'county') {
				data = {parent_id:$("#city").val(), area_type:3}; 
			}else{
				data = {area_type:1}; 
			}
			$.post(url, data, function(rs) {
				if (rs != null && rs.code=="200"
					&&rs.data!=null&&rs.data.length>0) {
					AddressSelector.showUI(type,0,rs.data);
				}
			}, "json");

		},
		showUI : function(selectedType,selectedValue, data) {
			var defaultString = '请选择省';
			if (selectedType == 'city') {
				defaultString = '请选择市/区';
			} else if (selectedType == 'county') {
				defaultString = '请选择区/县';
			}

			if (data != null && data.length > 0) {
				$('#'+selectedType).empty();
				var html = "<option value=\"0\">" + defaultString + "</option>";
				$(data)
						.each(
								function(i) {
									if (this.name == selectedValue) {
										html += "<option value=\"" + this.id
												+ "\"  selected>" + this.area_name
												+ "</option>";
									} else {
										html += "<option value=\"" + this.id
												+ "\" >" + this.area_name + "</option>";
									}
								});
				$('#'+selectedType).html(html);
				$('#'+selectedType).show();
				if(selectedType=='province'){
					this.refresh('city');
				}else if(selectedType=='city'){
					this.refresh('county');
				}
			} else {
				$('#'+selectedType).empty().hide();

			}

		}
	};

var regMobile = /^([0\+]?\d{1,4}\-)?(1[2-9]{1}[0-9]{9}|\d{8,10})$/;
var regTel = /^([0\+]?\d{1,4}\-)?((\d{1,4})-)?(\d{7,8})(-(\d{1,6}))?$/;
var regZip = /^\d{5,6}$/g;
function addCustomerDelivery(){
	//判断是修改 还是 新增
	var modifyId = $("#modifyId").val();
	if (modifyId.trim() == "") {
		modifyId=0;
	}
	var consignee = $("#consignee").val();
	if (consignee == '') {
		alert("请输入收货人姓名");
		return false;
	}
	if (consignee.length > 12) {
		alert("收货人姓名 不能超过12个字符或6个汉字");
		return false;
	}
	var mobile = $("#mobile").val(); 
	var tel = $("#tel").val();
	if (mobile == '' && tel=="") {
		alert("请填写固话或手机号！");
		return false;
	}else{
		if (mobile != '' && !mobile.match(regMobile)) {
			alert("手机号码格式不正确，请输入11位数字");
			return false;
		}
		if (tel!="" && !tel.match(regTel)) {
			alert("请正确输入固话格式");
			return false;
		}
	}
	var provinceId = $("#province").val();
	var cityId =  $("#city").val();
	var countyId = $("#county").val();

	if (!provinceId ||provinceId == '' || provinceId == 0) {
		alert("请输入收货省份");
		return false;
	}
	 
	if (!cityId || cityId == '' || cityId == 0) {
		alert("请输入收货市/区");
		return false;
	}
	 
	if (!countyId || countyId == '' || countyId == 0) {
		alert("请输入收货区/县");
		return false;
	}
	var address = $("#address").val();
	if (address == '') {
		alert("请输入收货详细地址");
		return false;
	}
	if (address.length > 250) {
		alert("收货人姓名 不能超过125个汉字");
		return false;
	}
	
	var provinceTxt="";
	var cityTxt = "";
	var countyTxt = "";  
	$('#province option').each(function(){
		if( $(this).attr("selected")){
			provinceTxt=$(this).text();
		}
	}); 
	$('#city option').each(function(){
		if( $(this).attr("selected")){
			cityTxt=$(this).text();
		}
	}); 
	$('#county option').each(function(){
		if( $(this).attr("selected")){
			countyTxt=$(this).text();
		}
	});  
	
	$.ajax({
		url: "/customer/delivery/add.do",
		type: "post",
		data: {"consignee" : consignee,
				"mobile" : mobile,
				"tel" : tel,
				"province" : provinceTxt,
				"city" : cityTxt,
				"district" : countyTxt,
				"address" : address
				
		},
		beforeSend:function(){
	    	$("#loading").show();
	    },
		success: function(data) {
			if (data.needlogin == true) {
				window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
				return ;
			}
			if (data.code == "200") {
				alert(data.msg);
				window.location.reload();
			} else {
				alert(data.msg);
			}
		},
		complete:function(){$("#loading").hide();}
	});
}
</script>
</body>
</html>