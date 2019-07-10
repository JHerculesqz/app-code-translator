<template>
  <div id="app">
    <div class="header">
      <div class="logo">去A神器</div>
      <div class="countNum">
        累计访问量：{{countNumber}}
      </div>
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
    <div class="footer">
      <div class="contentArea">
        <div class="payArea">
          <div class="payAreaText">赏</div>
          <div class="payAreaQRCode"></div>
        </div>
      </div>
      <div class="copyRightArea">© 2019 xxx</div>
    </div>
    <marvel-loading-icon-public ref="loading"></marvel-loading-icon-public>
    <marvel-confirm-ex :showConfirm="showConfirmEx"
                       :showOkBtn="showOkBtn4ConfirmEx"
                       :showCancelBtn="showCancelBtn4ConfirmEx"
                       :confirmTitle="confirmTitle4ConfirmEx"
                       :confirmIcon="confirmIcon4ConfirmEx"
                       :confirmIconColor="confirmIconColor4ConfirmEx"
                       :confirmSubTitle="confirmSubTitle4ConfirmEx"
                       :confirmCont="confirmExContent4ConfirmEx"
                       v-on:onClickOK="_onConfirmExOk"
                       v-on:onClickCancel="_onConfirmExCancel"></marvel-confirm-ex>
    <marvel-prompt-global :oPrompts="globalPrompt" :disposeTimeOut="disposeTimeOut"></marvel-prompt-global>
  </div>
</template>

<script>
  import Bus from "^/core/bus";
  import MarvelLoadingIconPublic from "^/widget/loading/MarvelLoadingIconPublic";
  import MarvelConfirmEx from "^/widget/dialog/MarvelConfirmEx";
  import MarvelPromptGlobal from "^/widget/prompt/MarvelPromptGlobal";
  import HttpUtils from "./components/0.common/httpUtil/httpUtils";
  export default {
    components:{
      MarvelLoadingIconPublic,
      MarvelConfirmEx,
      MarvelPromptGlobal,
    },
    name: 'app',
    data: function () {
      return {
        //#region const
        debug: false,
        //#endregion
        //#region confirmEx
        showConfirmEx: false,
        showOkBtn4ConfirmEx: true,
        showCancelBtn4ConfirmEx: true,
        confirmTitle4ConfirmEx: "",
        confirmIcon4ConfirmEx: "icon-cancel-circle",
        confirmIconColor4ConfirmEx: "#ff4040",
        confirmSubTitle4ConfirmEx: "Operation Failed",
        confirmExContent4ConfirmEx: [],
        funOnConfirmExOk:undefined,
        funOnConfirmExCancel:undefined,
        //#endregion
        //#region prompt
        globalPrompt:[],
        disposeTimeOut:3000,
        countNumber:0
        //#endregion
      }
    },
    mounted: function () {
      //#region custom
      this._initEx();
      //#endregion
    },
    methods: {
      //#region inner

      //#region lifeCycle

      _initEx: function () {
        //事件监听
        Bus.$on('msg', (e, params) => {
          if(e == "show-loading"){
            var key = params;
            this.$refs.loading.imsgMarvelLoadingPublicShow(key);
          }
          else if(e == "hide-loading"){
            var key = params;
            this.$refs.loading.imsgMarvelLoadingPublicHide(key);
          }
          else if(e == "show-confirmEx"){
            this._showConfirmEx(params)
          }
          else if(e == "add-prompt"){
            this._addPrompt(params)
          }
        })

        //get count number
        this._getCountNumber();
      },

      //#endregion

      _getCountNumber: function(){
        if (this.debug) {
          this.countNumber = 2000;
        }
        else {
          HttpUtils.post("count", {}).then(res => {
            this.countNumber = res.body.respDataJson;
          });
        }
      },

      //#region confirmEx

      _showConfirmEx: function (oParams) {
        this.showConfirmEx = true;
        this.showOkBtn4ConfirmEx = oParams.showOkButton;
        this.showCancelBtn4ConfirmEx = oParams.showCancelBtn;
        this.confirmTitle4ConfirmEx = oParams.confirmTitle;
        this.confirmIcon4ConfirmEx = oParams.confirmIcon;
        this.confirmIconColor4ConfirmEx = oParams.confirmIconColor;
        this.confirmSubTitle4ConfirmEx = oParams.confirmSubTitle;
        this.confirmExContent4ConfirmEx = oParams.confirmCont;
        this.funOnConfirmExOk = oParams.oAfterOk;
        this.funOnConfirmExCancel = oParams.oAfterCancel;
      },

      _onConfirmExOk: function() {
        this.showConfirmEx = false;
        this.funOnConfirmExOk();
      },

      _onConfirmExCancel: function(){
        this.showConfirmEx = false;
        this.funOnConfirmExCancel();
      },

      //#endregion

      //#region prompt

      _addPrompt: function (oParams) {
        this.globalPrompt.push(oParams);
      },

      //#endregion

      //#endregion
      //#region callback
      //#endregion
      //#region 3rd
      //#endregion
    }
  }
</script>

<style>
  html, body {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }

  body {
    position: fixed;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    font-family: Tahoma, Arial, Roboto, ”Droid Sans”, ”Helvetica Neue”, ”Droid Sans Fallback”, ”Heiti SC”, sans-self;
  }

  #app {
    height: 100%;
  }
  .header {
    width: 100%;
    height: 50px;
    position: absolute;
    top: 0;
    left: 0;
    background-color: #191c2d;
    z-index: 999;
  }

  .header .logo {
    width: 240px;
    height: 100%;
    float: left;
    color: #fff;
    font-size: 26px;
    font-weight: bold;
    line-height: 50px;
    text-align: left;
    margin-left: 10px;
  }

  .header .countNum{
    width: 240px;
    height: 100%;
    float: right;
    color: #fff;
    font-size: 18px;
    line-height: 50px;
    text-align: center;
  }

  .content {
    height: calc(100% - 250px);
    top: 50px;
    position: relative;
    background-color: #202335;
  }

  .footer {
    height: 200px;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    background-color: #0b0c13;
    font-size: 13px;
    line-height: 28px;
    text-align: center;
    color: rgba(255,255,255,0.4);
  }

  .contentArea{
    height: calc(100% - 28px);
  }

  .contentArea .payArea{
    width: 200px;
    margin: 0 auto;
  }

  .contentArea .payArea .payAreaText{
    margin-top: 60px;
    font-size: 24px;
    line-height: 52px;
    height: 52px;
    width: 52px;
    background-color: #191c2d;
    border-radius: 100px;
    color: rgba(255,255,255,0.5);
    float: left;
  }

  .contentArea .payArea .payAreaQRCode{
    margin-top: 30px;
    float: right;
    width: 120px;
    height: 120px;
    background: url("../../../static/images/qrcode.png") no-repeat center;
    background-size: contain;
    background-color: rgba(255,255,255,0.7);
  }

  .copyRightArea{
    height: 28px;
  }
</style>
