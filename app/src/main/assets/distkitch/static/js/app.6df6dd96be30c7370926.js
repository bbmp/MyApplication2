webpackJsonp([13],{"+B/h":function(e,n){},"+c27":function(e,n){},"02pT":function(e,n){},"1H7Z":function(e,n){},"3IMD":function(e,n){},"5Hoj":function(e,n){},"5I9V":function(e,n){},"6gXq":function(e,n){},"97dx":function(e,n){},CjYO:function(e,n){},"G/kn":function(e,n){},I7eg:function(e,n){},JKiR:function(e,n){},JsOw:function(e,n){},N9P1:function(e,n){},NAlg:function(e,n){},NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=t("7+uW"),o=t("Xxa5"),r=t.n(o),i=t("exGp"),c=t.n(i),s=t("JbOF"),u=t("vLgD");var p={name:"App",data:function(){return{}},mounted:function(){this.generateFingerprint()},methods:{generateFingerprint:function(){var e=this;return c()(r.a.mark(function n(){var t,a,o,i,c,p;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t={excludes:{enumerateDevices:!1}},e.next=3,s.a.load(t);case 3:return a=e.sent,e.next=6,a.get();case 6:o=e.sent,i=o.visitorId,console.log("visitorId",i),c=JSON.parse(localStorage.getItem("userInfoke")),p=c&&c.id?c.id:"",(n={userId:p,fingerId:i},Object(u.d)({url:"/cook-manage-admin/api/mau/analytic",method:"post",data:n})).then(function(e){console.log("标识结果：----",e)});case 12:case"end":return e.stop()}var n},n,e)}))()}}},l=function(){var e=this.$createElement,n=this._self._c||e;return n("div",{attrs:{id:"app"}},[n("keep-alive",[this.$route.meta.keepAlive?n("router-view"):this._e()],1),this._v(" "),this.$route.meta.keepAlive?this._e():n("router-view")],1)};l._withStripped=!0;var f={render:l,staticRenderFns:[]},m=f;var d=!1;var h=t("VU/8")(p,m,!1,function(e){d||t("xhze")},null,null);h.options.__file="src/App.vue";var g=h.exports,v=t("YaEn"),A=(t("N9P1"),t("Vhtp"),t("3Lne"),t("SSsa")),b=(t("FO5P"),t("woHG")),k=(t("LlGJ"),t("Wtz3")),y=(t("mMXg"),t("qYlo")),j=(t("nOaS"),t("pIDD")),_=(t("cZ0s"),t("fIxc")),C=(t("eqfM"),t("/QYm")),O=(t("nsOR"),t("WMi1")),w=(t("XmAh"),t("il3B")),I=(t("JRZP"),t("LK01")),x=(t("ZuV/"),t("37Xn")),P=(t("OWWB"),t("1fWZ")),D=(t("i0mo"),t("Hkar")),L=(t("k3b4"),t("+2ln")),R=(t("MY4N"),t("0zAV")),T=(t("81cA"),t("uGb/")),F=(t("RgoE"),t("0KWt")),M=(t("yIEv"),t("OIh9")),S=(t("jAcA"),t("86U2")),U=(t("9++/"),t("QhyB")),W=(t("1E9F"),t("2Ux5")),Z=(t("yffH"),t("sdMh")),E=(t("oXr1"),t("wbAJ")),H=(t("OLZS"),t("4j1Q")),q=(t("3AsM"),t("7ZPY")),G=(t("zP7x"),t("rD0v")),J=(t("P/b9"),t("tubq"));t("sEnP");a.a.use(A.a).use(b.a).use(k.a).use(y.a).use(j.a).use(_.a).use(C.a).use(O.a).use(w.a).use(I.a).use(x.a).use(P.a).use(D.a).use(L.a).use(R.a).use(T.a).use(F.a).use(M.a).use(S.a).use(U.a).use(W.a).use(Z.a).use(E.a).use(H.a).use(q.a).use(G.a).use(J.a);var X=t("Lw6n"),Y=t.n(X);a.a.config.productionTip=!1;new Y.a;v.a.beforeEach(function(e,n,t){e.meta.title&&(document.title=e.meta.title),t()}),new a.a({el:"#app",router:v.a,components:{App:g},template:"<App/>"})},OCj8:function(e,n,t){(e.exports=t("FZ+f")(!0)).push([e.i,'\n:root{--safe-area-inset-bottom: 0px\n}\n@supports(top: env(safe-area-inset-top)){\n:root{--safe-area-inset-bottom: constant(safe-area-inset-bottom)\n}\n}\n@supports(top: env(safe-area-inset-top)){\n:root{--safe-area-inset-bottom: env(safe-area-inset-bottom)\n}\n}\n#app{font-family:"Avenir",Helvetica,Arial,sans-serif;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale;text-align:center;color:#2c3e50\n}',"",{version:3,sources:["D:/projects/robam/h5-vue-update/src/D:/projects/robam/h5-vue-update/src/App.vue"],names:[],mappings:";AAyDA,MACG,6BAAA;CAEH;AAAA;AACI,MACG,0DAAA;CAAA;CAGP;AAAA;AACI,MACI,qDAAA;CAAA;CAGR;AAAA,KACE,gDACA,mCACA,kCACA,kBACA,aAAA;CAAA",file:"App.vue",sourcesContent:["\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\r\n:root{\r\n   --safe-area-inset-bottom: 0px;  \r\n}\r\n@supports (top: env(safe-area-inset-top)) {  \r\n    :root {  \r\n       --safe-area-inset-bottom: constant(safe-area-inset-bottom);  \r\n    }  \r\n}\r\n@supports (top: env(safe-area-inset-top)) {  \r\n    :root {  \r\n        --safe-area-inset-bottom: env(safe-area-inset-bottom);  \r\n    }  \r\n} \r\n#app {\r\n  font-family: 'Avenir', Helvetica, Arial, sans-serif;\r\n  -webkit-font-smoothing: antialiased;\r\n  -moz-osx-font-smoothing: grayscale;\r\n  text-align: center;\r\n  color: #2c3e50;\r\n  //   background: #f5f5f8;\r\n}\r\n"],sourceRoot:""}])},RUOb:function(e,n){},RsMb:function(e,n){},S6Ip:function(e,n){},T2s0:function(e,n){},TIfe:function(e,n,t){"use strict";n.b=function(e,n){return o.a.set("access_token",e,{expires:n,domain:r})},n.a=function(){return o.a.get("refresh_token")},n.c=function(e,n){return o.a.set("refresh_token",e,{expires:n,domain:r})};var a=t("lbHh"),o=t.n(a),r=".myroki.com"},Vhtp:function(e,n){},W0KU:function(e,n){},WpgC:function(e,n){},YaEn:function(e,n,t){"use strict";var a=t("7+uW"),o=t("/ocq");a.a.use(o.a),n.a=new o.a({routes:[{path:"/guideList",name:"guideList",component:function(e){return t.e(10).then(function(){var n=[t("/oJK")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"索引"}},{path:"/",name:"root",redirect:"/guideList"},{path:"/lineViewsFromRokiApp",name:"lineViewsFromRokiApp",component:function(e){return Promise.all([t.e(3),t.e(0)]).then(function(){var n=[t("wz7W")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"多段烹饪",keepAlive:!0}},{path:"/login",name:"login",component:function(e){return Promise.all([t.e(0),t.e(9)]).then(function(){var n=[t("PW+8")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"ROKI登录",keepAlive:!1}},{path:"/lotteryDraw",name:"newKitchenDay",component:function(e){return Promise.all([t.e(2),t.e(0)]).then(function(){var n=[t("Cf+m")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"抽奖",keepAlive:!0}},{path:"/testPoster",name:"testPoster",component:function(e){return t.e(4).then(function(){var n=[t("9RLD")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"ceshi",keepAlive:!0}},{path:"/address/:userId",name:"addressEdit",component:function(e){return Promise.all([t.e(0),t.e(5)]).then(function(){var n=[t("IgYk")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"收货地址",keepAlive:!1}},{path:"/myRewards/:userId",name:"myRewards",component:function(e){return t.e(8).then(function(){var n=[t("yJap")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"我的奖品",keepAlive:!1}},{path:"/bannerPages/cookCulture",name:"cookCulture",component:function(e){return t.e(7).then(function(){var n=[t("yu29")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"ROBAM老板 | 烹饪文化活动大预告",keepAlive:!0}},{path:"/rewardInfo",name:"rewardInfo",component:function(e){return t.e(6).then(function(){var n=[t("gGZx")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"大家来找茬",keepAlive:!0}},{path:"/juneLottery",name:"juneLottery",component:function(e){return Promise.all([t.e(1),t.e(0)]).then(function(){var n=[t("ZBop")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"618宠粉抽奖",keepAlive:!0}},{path:"/androidTest",name:"androidTest",component:function(e){return Promise.all([t.e(0),t.e(11)]).then(function(){var n=[t("HPXG")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{title:"安卓测试"}}]})},ZZgd:function(e,n){},bFPQ:function(e,n){},eh36:function(e,n){},fykb:function(e,n,t){"use strict";n.d=function(e){return Object(a.c)({url:"/cook-manage-admin/api/ops/activity/lottery/userInit",method:"get",params:e})},n.e=function(){return Object(a.d)({url:"/cook-manage-admin/api/ops/activity/lottery/statisticsCount",method:"get"})},n.g=function(e){return Object(a.c)({url:"/cook-manage-admin/api/ops/activity/lottery/share",method:"get",params:e})},n.a=function(e){return Object(a.d)({url:"/cook-manage-admin/api/ops/activity/lottery/clickShare",method:"get",params:e})},n.b=function(e){return Object(a.c)({url:"/cook-manage-admin/api/ops/activity/lottery/lottery",method:"get",params:e})},n.c=function(e){return Object(a.c)({url:"/cook-manage-admin/api/ops/activity/lottery/getSign",method:"get",params:e})},n.f=function(e){return Object(a.c)(e)};var a=t("vLgD")},hW8u:function(e,n){},lyXt:function(e,n,t){"use strict";n.b=function(e){return Object(a.b)({url:"/rest/ums/api/dynamic-pwd/get",method:"post",data:e})},n.c=function(e){return Object(a.a)({url:"/rest/auth/api/oauth/token?grant_type=roki",method:"get",params:e})},n.a=function(e){return Object(a.b)({url:"/rest/ums/api/user/get/oauth",method:"get",headers:{Authorization:e}})},n.d=function(e){return Object(a.a)({url:"/rest/auth/api/oauth/token?grant_type=refresh_token",method:"get",params:e})};var a=t("vLgD")},mSI1:function(e,n){},nLpc:function(e,n){},nOtf:function(e,n){},nsZj:function(e,n){},px3J:function(e,n){},qpP9:function(e,n){},sEnP:function(e,n){},sKgQ:function(e,n){},v4pe:function(e,n){},vLgD:function(e,n,t){"use strict";t.d(n,"e",function(){return m}),t.d(n,"b",function(){return g}),t.d(n,"a",function(){return v}),t.d(n,"c",function(){return A}),t.d(n,"d",function(){return b});t("XmAh");var a=t("il3B"),o=(t("eqfM"),t("/QYm")),r=t("//Fk"),i=t.n(r),c=t("mtWM"),s=t.n(c),u=t("TIfe"),p=t("lyXt"),l=t("fykb"),f=t("YaEn");s.a.defaults.withCredentials=!0,s.a.defaults.timeout=1e4;var m="http://develop.h5.myroki.com/distkitch/index.html#",d="",h="";d="http://develop.api.myroki.com",h="http://develop.manage.myroki.com";var g=function(e){return new i.a(function(n,t){s.a.create({headers:{"Content-Type":"application/json;charset=UTF-8"},baseURL:d})(e).then(function(e){if(console.log("request-----res----",e),0===e.data.rc)n(e.data);else{var a=e.data.msg||e.data.error_description||e.data.error||"未知错误";o.a.fail(a),t("error")}}).catch(function(e){e.response&&e.response.data&&e.response.data.error_description?o.a.fail(e.response.data.error_description):o.a.fail("请求异常"),t(e)})})},v=function(e){return new i.a(function(n,t){s.a.create({headers:{"Content-Type":"application/json;charset=UTF-8","Cache-Control":"no-cache"},baseURL:d})(e).then(function(e){console.log("login-res----",e),n(e.data)}).catch(function(e){e.response&&e.response.data&&e.response.data.error_description?o.a.fail(e.response.data.error_description):o.a.fail("请求异常"),t(e)})})},A=function(e){return console.log("iiiii-----",localStorage.getItem("accessToken")),new i.a(function(n,t){var r=localStorage.getItem("terminal");s.a.create({headers:{"Content-Type":"application/json;charset=UTF-8",clientId:"app"==r?"roki_client":"roki_h5_client"},baseURL:h+"/api-cook-manage"})(e).then(function(r){console.log("request-----res----",r);var i=r.data.code;if(200===i)n(r.data);else if(401===i)if("app"!=localStorage.getItem("terminal")){var c={client_id:"roki_h5_client",client_secret:"test",refresh_token:Object(u.a)()};Object(p.d)(c).then(function(o){if(o.access_token){var r=new Date((new Date).getTime()+1e3*o.expires_in);Object(u.b)(o.access_token,r),Object(u.c)(o.refresh_token,180),Object(l.f)(e).then(function(e){console.log("rrrrrr----",e),n(e)}).catch(function(e){a.a.confirm({title:"提示",message:"登录过期,请重新登录"}).then(function(){console.log("router",f.a),f.a.push("/login")}),t("error")})}else a.a.confirm({title:"提示",message:"登录过期,请重新登录"}).then(function(){console.log("router",f.a),f.a.push("/login")}),t("error")}).catch(function(e){a.a.confirm({title:"提示",message:"登录过期,请重新登录"}).then(function(){console.log("router",f.a),f.a.push("/login")}),t("error")})}else t("error");else{var s=r.data.message||r.data.msg||r.data.error_description||r.data.error||"未知错误";o.a.fail(s),t("error")}}).catch(function(e){o.a.fail("请求异常"),t(e)})})},b=function(e){return new i.a(function(n,t){s.a.create({headers:{"Content-Type":"application/json;charset=UTF-8"},baseURL:h+"/api-cook-manage"})(e).then(function(e){if(console.log("request-----res----",e),200===e.data.code)n(e.data);else{var a=e.data.message||e.data.msg||e.data.error_description||e.data.error||"未知错误";o.a.fail(a),t("error")}}).catch(function(e){o.a.fail("请求异常"),t(e)})})}},xhze:function(e,n,t){var a=t("OCj8");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("dec867e8",a,!1,{})},yU4Z:function(e,n){}},["NHnr"]);
//# sourceMappingURL=app.6df6dd96be30c7370926.js.map