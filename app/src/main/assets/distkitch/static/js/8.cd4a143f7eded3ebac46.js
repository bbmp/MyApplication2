webpackJsonp([8],{"4lUv":function(n,t,r){"use strict";t.a=function(n){return Object(e.c)({url:"/cook-manage-admin/api/ops/activity/lottery/myPrize?userId="+n,method:"get"})},t.c=function(n){return Object(e.c)({url:"/cook-manage-admin/api/ops/activity/lottery/saveAddress",method:"post",data:n})},t.b=function(n){return Object(e.c)({url:"/cook-manage-admin/api/ops/activity/lottery/userInit?userId="+n,method:"get"})};var e=r("vLgD")},"WtK/":function(n,t,r){(n.exports=r("FZ+f")(!0)).push([n.i,"\n.reward-outter[data-v-0cbb4779]{min-height:calc(100% + var(--safe-area-inset-bottom))\n}\n.rewards-list[data-v-0cbb4779]{width:100%;font-family:PingFang SC\n}\n.rewards-list ul[data-v-0cbb4779]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-ms-flex-direction:column;flex-direction:column\n}\n.rewards-list ul li[data-v-0cbb4779]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-pack:justify;-ms-flex-pack:justify;justify-content:space-between;-webkit-box-align:center;-ms-flex-align:center;align-items:center;padding:4.8vw 4vw 4.266667vw;margin-bottom:1.333333vw;background:#fff\n}\n.rewards-list ul li .reward-name[data-v-0cbb4779]{text-align:left;margin-bottom:3.2vw;font-size:4vw;font-weight:500;color:#333\n}\n.rewards-list ul li .reward-time[data-v-0cbb4779]{font-size:3.2vw;color:#666\n}\n.rewards-list ul li[data-v-0cbb4779]:first-child{margin-top:2.533333vw\n}\n.rewards-list ul li .reward-address .status[data-v-0cbb4779]{font-size:3.733333vw;font-weight:400;color:#2871f8\n}\n.rewards-none[data-v-0cbb4779]{padding-top:67.733333vw\n}\n.rewards-none p[data-v-0cbb4779]{margin:0;font-size:4.8vw;color:#333\n}","",{version:3,sources:["D:/projects/robam/h5-vue-update/src/views/rokiMarchActivity/myRewards/D:/projects/robam/h5-vue-update/src/views/rokiMarchActivity/myRewards/index.vue"],names:[],mappings:";AAgEA,gCACE,qDAAA;CAEF;AAAA,+BACE,WAEA,uBAAA;CACA;AAAA,kCACE,oBAAA,oBAAA,aACA,4BAAA,6BAAA,0BAAA,qBAAA;CACA;AAAA,qCACE,oBAAA,oBAAA,aACA,yBAAA,sBAAA,8BACA,yBAAA,sBAAA,mBACA,6BACA,yBACA,eAAA;CACA;AAAA,kDACE,gBACA,oBACA,cACA,gBACA,UAAA;CAEF;AAAA,kDACE,gBACA,UAAA;CAEF;AAAA,iDACE,qBAAA;CAGA;AAAA,6DACE,qBAEA,gBACA,aAAA;CAMV;AAAA,+BACE,uBAAA;CACA;AAAA,iCACE,SACA,gBACA,UAAA;CAAA",file:"index.vue",sourcesContent:["\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\r\n.reward-outter {\r\n  min-height: calc(100% + var(--safe-area-inset-bottom));\r\n}\r\n.rewards-list {\r\n  width: 100%;\r\n\r\n  font-family: PingFang SC;\r\n  ul {\r\n    display: flex;\r\n    flex-direction: column;\r\n    li {\r\n      display: flex;\r\n      justify-content: space-between;\r\n      align-items: center;\r\n      padding: 36px 30px 32px;\r\n      margin-bottom: 10px;\r\n      background: #fff;\r\n      .reward-name {\r\n        text-align: left;\r\n        margin-bottom: 24px;\r\n        font-size: 30px;\r\n        font-weight: 500;\r\n        color: #333333;\r\n      }\r\n      .reward-time {\r\n        font-size: 24px;\r\n        color: #666666;\r\n      }\r\n      &:first-child {\r\n        margin-top: 19px;\r\n      }\r\n      .reward-address {\r\n        .status {\r\n          font-size: 28px;\r\n\r\n          font-weight: 400;\r\n          color: #2871f8;\r\n        }\r\n      }\r\n    }\r\n  }\r\n}\r\n.rewards-none {\r\n  padding-top: 508px;\r\n  p {\r\n    margin: 0;\r\n    font-size: 36px;\r\n    color: #333333;\r\n  }\r\n}\r\n"],sourceRoot:""}])},yJap:function(n,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var e=r("4lUv"),a={name:"myRewards",data:function(){return{rewardList:[],isShowtip:!1}},created:function(){var n=this,t=this.$route.params.userId;Object(e.a)(t).then(function(t){200==t.code&&(n.rewardList=t.data,n.isShowtip=!t.data||!t.data.length)})},mounted:function(){document.querySelector("body").setAttribute("style","background:#f5f5f8")},methods:{editAddress:function(){var n=this.$route.params.userId,t=this.$route.query.terminal;this.$router.push("/address/"+n+"?terminal="+t)}},beforeRouteLeave:function(n,t,r){document.querySelector("body").removeAttribute("style"),r()}},i=function(){var n=this,t=n.$createElement,r=n._self._c||t;return r("div",{staticClass:"reward-outter"},[r("div",{staticClass:"rewards-list"},[n.rewardList.length>0?r("ul",n._l(n.rewardList,function(t){return r("li",[r("div",{staticClass:"reward-info"},[r("div",{staticClass:"reward-name"},[n._v(n._s(t.prizeName))]),n._v(" "),r("div",{staticClass:"reward-time"},[n._v(n._s(t.createDatetime))])]),n._v(" "),5!=t.prizeId?r("div",{staticClass:"reward-address"},[r("div",{staticClass:"status",on:{click:n.editAddress}},[n._v(n._s(t.prizeStatus))])]):n._e()])}),0):n._e(),n._v(" "),n.isShowtip?r("div",{staticClass:"rewards-none"},[r("p",[n._v("您还没有抽过奖哦！")]),n._v(" "),r("p",[n._v("赶快去参加活动吧～")])]):n._e()])])};i._withStripped=!0;var s={render:i,staticRenderFns:[]},A=s;var o=!1;var d=r("VU/8")(a,A,!1,function(n){o||r("z8KA")},"data-v-0cbb4779",null);d.options.__file="src/views/rokiMarchActivity/myRewards/index.vue";t.default=d.exports},z8KA:function(n,t,r){var e=r("WtK/");"string"==typeof e&&(e=[[n.i,e,""]]),e.locals&&(n.exports=e.locals);r("rjj0")("2186bf02",e,!1,{})}});
//# sourceMappingURL=8.cd4a143f7eded3ebac46.js.map