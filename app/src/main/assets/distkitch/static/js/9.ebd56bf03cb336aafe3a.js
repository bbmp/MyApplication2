webpackJsonp([9],{"4BFE":function(n,e,t){(n.exports=t("FZ+f")(!0)).push([n.i,"\ninput[data-v-4f5b068d]:-webkit-autofill{-webkit-box-shadow:0 0 0px 1000px #fff inset !important\n}\n.title[data-v-4f5b068d]{text-align:left;font-size:24px;font-family:PingFang SC-Bold,PingFang SC;font-weight:bold;color:#222;margin:0px 28px 74px;line-height:28px;padding-top:73px\n}\n.phone[data-v-4f5b068d],.code[data-v-4f5b068d]{margin:0 28px;border-bottom:1px solid #e5e5e5;font-size:16px;font-family:PingFang SC;font-weight:500;color:#333\n}\n.code[data-v-4f5b068d]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-pack:justify;-ms-flex-pack:justify;justify-content:space-between;-webkit-box-align:center;-ms-flex-align:center;align-items:center\n}\n.phone .phone-input[data-v-4f5b068d]{width:100%;border:none;padding:19px 0;outline:none\n}\n.code .veri-input[data-v-4f5b068d]{border:none;padding:19px 0;outline:none;-webkit-box-flex:1;-ms-flex:1;flex:1\n}\n.code .veri-btn[data-v-4f5b068d]{width:130px;height:30px;background:rgba(239,206,23,.1);border-radius:15px;border:none;font-size:14px;font-family:PingFang SC;font-weight:500;color:#efaa17;-webkit-box-flex:0;-ms-flex:0 0 130px;flex:0 0 130px;outline:none;text-align:center\n}\n.login-btn[data-v-4f5b068d]{width:90%;height:45px;background:#f2b33e;border-radius:22px;font-size:16px;font-family:PingFang SC;font-weight:500;color:#fff;line-height:45px;text-align:center;margin:35px auto 20px;outline:none;border:none\n}\n.code .disabled-style[data-v-4f5b068d]{background-color:#b3b3b3;color:#fff\n}\n.protol-box[data-v-4f5b068d]{font-size:12px;font-family:PingFang SC;font-weight:400;color:#333;margin-left:43px;display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-align:center;-ms-flex-align:center;align-items:center\n}\n.protol[data-v-4f5b068d]{color:#efaa17\n}\n.keep-btn[data-v-4f5b068d]{padding:35px 28px 20px;font-size:17px;font-family:Roboto-Regular,Roboto;font-weight:400\n}","",{version:3,sources:["D:/projects/robam/h5-vue-update/src/views/webLoginRoki/D:/projects/robam/h5-vue-update/src/views/webLoginRoki/index.vue"],names:[],mappings:";AAsLA,wCACI,uDAAA;CAEJ;AAAA,wBACG,gBACC,eACA,yCACA,iBACA,WACA,qBACA,iBACA,gBAAA;CAEH;AAAA,+CACO,cAEA,gCACA,eACA,wBACA,gBACA,UAAA;CAEJ;AAAA,uBACI,oBAAA,oBAAA,aACA,yBAAA,sBAAA,8BACA,yBAAA,sBAAA,kBAAA;CAEJ;AAAA,qCACI,WACA,YACA,eACA,YAAA;CAEJ;AAAA,mCACI,YACA,eACA,aACA,mBAAA,WAAA,MAAA;CAEJ;AAAA,iCACI,YACA,YACA,+BACA,mBACA,YACA,eACA,wBACA,gBACA,cACA,mBAAA,mBAAA,eACA,aACA,iBAAA;CAEJ;AAAA,4BACI,UACA,YACA,mBACA,mBACA,eACA,wBACA,gBACA,WACA,iBACA,kBACA,sBACA,aACA,WAAA;CAEJ;AAAA,uCACI,yBACA,UAAA;CAEJ;AAAA,6BACI,eACA,wBACA,gBACA,WACA,iBACA,oBAAA,oBAAA,aAEA,yBAAA,sBAAA,kBAAA;CAEJ;AAAA,yBACI,aAAA;CAEJ;AAAA,2BACA,uBACA,eACA,kCACA,eAAA;CAAA",file:"index.vue",sourcesContent:["\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\r\ninput:-webkit-autofill {\r\n    -webkit-box-shadow: 0 0 0px 1000px white inset !important;\r\n}\r\n.title{\r\n   text-align:left;\r\n    font-size: 24px;\r\n    font-family: PingFang SC-Bold, PingFang SC;\r\n    font-weight: bold;\r\n    color: #222222;\r\n    margin:0px 28px 74px;\r\n    line-height: 28px;\r\n    padding-top: 73px;\r\n}\r\n .phone,.code{\r\n        margin: 0 28px;\r\n        // height: 60px;\r\n        border-bottom: 1px solid #E5E5E5;\r\n        font-size: 16px;\r\n        font-family: PingFang SC;\r\n        font-weight: 500;\r\n        color: #333333;\r\n    }\r\n    .code{\r\n        display: flex;\r\n        justify-content: space-between;\r\n        align-items: center;\r\n    }\r\n    .phone .phone-input{\r\n        width: 100%;\r\n        border: none;\r\n        padding: 19px 0;\r\n        outline: none;\r\n    }\r\n    .code .veri-input{\r\n        border: none;\r\n        padding: 19px 0;\r\n        outline: none;\r\n        flex: 1;\r\n    }\r\n    .code .veri-btn{\r\n        width: 130px;\r\n        height: 30px;\r\n        background: rgba(239, 206, 23, 0.1);\r\n        border-radius: 15px;\r\n        border: none;\r\n        font-size: 14px;\r\n        font-family: PingFang SC;\r\n        font-weight: 500;\r\n        color: #EFAA17;\r\n        flex: 0 0 130px;\r\n        outline: none;\r\n        text-align: center;\r\n    }\r\n    .login-btn{\r\n        width: 90%;\r\n        height: 45px;\r\n        background: #F2B33E;\r\n        border-radius: 22px;\r\n        font-size: 16px;\r\n        font-family: PingFang SC;\r\n        font-weight: 500;\r\n        color: #fff;\r\n        line-height:45px;\r\n        text-align:center;\r\n        margin: 35px auto 20px;\r\n        outline: none;\r\n        border: none;\r\n    }\r\n    .code .disabled-style{\r\n        background-color:#b3b3b3;\r\n        color:#fff;\r\n    }\r\n    .protol-box{\r\n        font-size: 12px;\r\n        font-family: PingFang SC;\r\n        font-weight: 400;\r\n        color: #333333;\r\n        margin-left: 43px;\r\n        display:flex;\r\n        //justify-content: center;\r\n        align-items: center;\r\n    }\r\n    .protol{\r\n        color: #EFAA17;\r\n    }\r\n    .keep-btn{\r\n    padding:35px 28px 20px;\r\n    font-size: 17px;\r\n    font-family: Roboto-Regular, Roboto;\r\n    font-weight: 400;\r\n    }\r\n\r\n"],sourceRoot:""}])},"PW+8":function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=t("mvHQ"),i=t.n(o),r=t("0xDb"),a=t("RJYW"),s=t("lyXt"),A=t("TIfe"),l={name:"login",components:{},data:function(){return{phone:"",vercode:"",isDisabled:!1,codeText:"获取验证码",checked:!1}},mounted:function(){window.scrollTo(0,0),console.log(this.$route.query)},methods:{getVerCode:function(){var n=this,e=this.phone.trim();e?Object(r.g)(e)?(this.countDown(60),Object(s.b)({phone:e}).then(function(e){n.$toast("发送验证码成功")})):this.$toast("手机号有误"):this.$toast("请输入手机号")},countDown:function(n){var e=this;0==n?(this.isDisabled=!1,this.codeText="获取验证码"):(this.isDisabled=!0,this.codeText=n+"s后重新获取",n--,setTimeout(function(){e.countDown(n)},1e3))},gotoLogin:function(){var n=this,e=this.phone.trim(),t=this.vercode.trim();if(e)if(Object(r.g)(e))if(t)if(6==t.length&&/^\d+$/.test(t))if(this.checked){var o={loginType:"mobileSmsCode",sjhm:e,smsCode:t,password:"",accessToken:"",refreshToken:"",openId:"",client_id:"roki_h5_client",client_secret:"test",appType:"RWWEX"};Object(s.c)(o).then(function(e){if(e.access_token){n.$toast("登录成功");var t=new Date((new Date).getTime()+1e3*e.expires_in);Object(A.b)(e.access_token,t),Object(A.c)(e.refresh_token,180);var o="bearer "+e.access_token;n.getUserInfo(o)}})}else this.$toast("请勾选协议");else this.$toast("验证码有误");else this.$toast("请输入验证码");else this.$toast("手机号有误");else this.$toast("请输入手机号")},getUserInfo:function(n){var e=this;Object(s.a)(n).then(function(n){var t=e.$route.query,o=t.lastLabel,r=t.mutiId;localStorage.setItem("userInfoke",i()(n.user));var s=n.user.id;"keepMuti"===o?Object(a.c)(r,{userId:s}).then(function(n){e.$toast("保存多段信息成功"),e.$router.go(-1)}):"goReward"===o?e.$router.replace("/myRewards/"+s):e.$router.go(-1)})}}},c=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[n._m(0),n._v(" "),t("div",{staticClass:"phone"},[t("input",{directives:[{name:"model",rawName:"v-model",value:n.phone,expression:"phone"}],staticClass:"phone-input",attrs:{autocomplete:"off",type:"tel",name:"sjhm",placeholder:"请输入手机号(新号码自动注册)",maxlength:"11",id:"mobileVal"},domProps:{value:n.phone},on:{input:function(e){e.target.composing||(n.phone=e.target.value)}}})]),n._v(" "),t("div",{staticClass:"code"},[t("input",{directives:[{name:"model",rawName:"v-model",value:n.vercode,expression:"vercode"}],staticClass:"veri-input",attrs:{placeholder:"请输入验证码",autocomplete:"off",maxlength:"6",id:"verifyVal"},domProps:{value:n.vercode},on:{input:function(e){e.target.composing||(n.vercode=e.target.value)}}}),n._v(" "),t("input",{staticClass:"veri-btn",class:{"disabled-style":n.isDisabled},attrs:{disabled:n.isDisabled,readonly:""},domProps:{value:n.codeText},on:{click:n.getVerCode}})]),n._v(" "),t("div",{staticClass:"login-box"},[t("div",{staticClass:"keep-btn"},[t("van-button",{staticStyle:{"font-size":"17px","font-weight":"400"},attrs:{color:"#F2B33E",size:"large",round:!0},on:{click:n.gotoLogin}},[n._v("登录")])],1)]),n._v(" "),t("div",{staticClass:"protol-box"},[t("van-checkbox",{attrs:{"icon-size":"14px"},model:{value:n.checked,callback:function(e){n.checked=e},expression:"checked"}}),n._v(" "),n._m(1)],1)])};c._withStripped=!0;var d={render:c,staticRenderFns:[function(){var n=this.$createElement,e=this._self._c||n;return e("div",{staticClass:"title"},[this._v("你好，"),e("br"),this._v("欢迎登录ROKI")])},function(){var n=this.$createElement,e=this._self._c||n;return e("div",{staticStyle:{"margin-left":"6px"}},[e("span",[this._v("已阅读并同意")]),e("span",{staticClass:"protol"},[e("a",{attrs:{href:"https://h5.myroki.com/userAgreement/userNotice.html"}},[this._v("《ROKI用户协议》")])]),this._v("和"),e("span",{staticClass:"protol"},[e("a",{attrs:{href:"https://h5.myroki.com/userAgreement/registerAgreement.html"}},[this._v("《隐私政策》")])])])}]},p=d;var f=!1;var g=t("VU/8")(l,p,!1,function(n){f||t("iGrh")},"data-v-4f5b068d",null);g.options.__file="src/views/webLoginRoki/index.vue";e.default=g.exports},RJYW:function(n,e,t){"use strict";e.a=function(n){return Object(o.b)({url:"/rest/cks/api/multi/"+n,method:"get"})},e.b=function(n){return Object(o.b)({url:"/rest/cks/api/cookbook/by-tag-other/get/cooks",method:"post",data:n})},e.c=function(n,e){return Object(o.b)({url:"/rest/cks/api/multi/share/"+n,method:"post",data:e})};var o=t("vLgD")},iGrh:function(n,e,t){var o=t("4BFE");"string"==typeof o&&(o=[[n.i,o,""]]),o.locals&&(n.exports=o.locals);t("rjj0")("085bac9c",o,!1,{})}});
//# sourceMappingURL=9.ebd56bf03cb336aafe3a.js.map