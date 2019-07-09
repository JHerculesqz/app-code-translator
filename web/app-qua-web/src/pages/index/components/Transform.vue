<template>
  <div class="wrapper4TransArea blackBg">
    <div class="toolArea">
      <div class="dropDownArea">
        <marvel-drop-down-button ref="languageSelect" width="200px" classCustom="btnCustom"
                                 maxHeight="240px"
                                 v-on:onOptionSelect="_onOptionSelect"></marvel-drop-down-button>
      </div>
      <div class="buttonArea">
        <marvel-primary-button classCustom="classCustom1" label="Transform"
                               v-on:onClick="_onClickToTransForm"></marvel-primary-button>
      </div>
    </div>
    <div class="textArea">
      <div class="textAreaLeft">
        <div class="titleArea icon-file-text2">{{leftEditorTitle}}:</div>
        <div class="aceTextArea">
          <marvel-ace-editor ref="aceEditorLeft" theme="dracula" :fontSize="14" :readOnly="false"></marvel-ace-editor>
        </div>
      </div>
      <div class="textAreaRight">
        <div class="titleArea icon-file-text2">{{rightEditorTitle}}:</div>
        <div class="aceTextArea">
          <marvel-ace-editor ref="aceEditorRight" theme="dracula" :fontSize="14" :readOnly="false"></marvel-ace-editor>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import MarvelDropDownButton from "^/widget/button/MarvelDropDownButton";
  import MarvelAceEditor from "^/widget/aceEditor/MarvelAceEditor";
  import MarvelPrimaryButton from "^/widget/button/MarvelPrimaryButton";
  import HttpUtils from "./0.common/httpUtil/httpUtils";
  import mockUtils from "./0.common/mock/index";

  /**
   * Transform component description
   * @vuedoc
   * @exports Transform
   */
  export default {
    name: 'Transform',
    components: {
      MarvelPrimaryButton,
      MarvelAceEditor,
      MarvelDropDownButton
    },
    data() {
      return {
        //#region const
        debug: false,
        //#endregion
        languageOptions: [{
          key1: "MySQL",
          key2: "GaussSQL",
          label: "MySQL to GaussSQL",
          languageAceEditor1: "mysql",
          languageAceEditor2: "sql",
          languagePostParam1: "mysql",
          languagePostParam2: "sql",
        },{
          key1: "C++",
          key2: "Java",
          label: "C++ to Java",
          languageAceEditor1: "c_cpp",
          languageAceEditor2: "java",
          languagePostParam1: "c_cpp",
          languagePostParam2: "java",
        },{
          key1: "C",
          key2: "Java",
          label: "C to Java",
          languageAceEditor1: "c_cpp",
          languageAceEditor2: "java",
          languagePostParam1: "c",
          languagePostParam2: "java",
        },{
          key1: "Lua",
          key2: "Python",
          label: "Lua to Python",
          languageAceEditor1: "lua",
          languageAceEditor2: "python",
          languagePostParam1: "lua",
          languagePostParam2: "python",
        },{
          key1: "TCL",
          key2: "Python",
          label: "TCL to Python",
          languageAceEditor1: "tcl",
          languageAceEditor2: "python",
          languagePostParam1: "tcl",
          languagePostParam2: "python",
        }],
        leftEditorTitle:"MySQL",
        rightEditorTitle:"GaussSQL",
      }
    },
    mounted: function () {
      //#region init

      this._initEx();

      //#endregion
    },
    methods: {
      //#region inner

      //#region lifecycle

      _initEx: function () {
        this.$refs.languageSelect.init(this.languageOptions, this.languageOptions[0].label);
        this._onOptionSelect(this.languageOptions[0]);
      },

      //#endregion

      _onOptionSelect: function (oItem) {
        this.leftEditorTitle = oItem.key1;
        this.$refs.aceEditorLeft.setCode("", oItem.languageAceEditor1);

        this.rightEditorTitle = oItem.key2;
        this.$refs.aceEditorRight.setCode("", oItem.languageAceEditor2);
      },
      _getLeftCode: function () {
        return this.$refs.aceEditorLeft.getCode()
      },
      _getRightCode: function () {
        return this.$refs.aceEditorRight.getCode()
      },

      _onClickToTransForm: function () {
        var oSelectObj = this.$refs.languageSelect.getSelectItemObj();
        var tansformRes = undefined;
        var oParams = {
          strReqBody: this._getLeftCode(),
          lan: oSelectObj.languagePostParam1,
        };
        var reqBody = JSON.stringify(oParams);
        if (this.debug) {
          tansformRes= mockUtils.getTransformRes(oSelectObj.key2);
          this.$refs.aceEditorRight.setCode(tansformRes, oSelectObj.languageAceEditor2);
          this.$refs.aceEditorRight.formatCode();
        }
        else {
          HttpUtils.post("parser", reqBody).then(res => {
            tansformRes = res.body.respDataJson;
            this.$refs.aceEditorRight.setCode(tansformRes, oSelectObj.languageAceEditor2);
            this.$refs.aceEditorRight.formatCode();
          });
        }
      }

      //#endregion
      //#region callback
      //#endregion
      //#region 3rd
      //#endregion

    }
  }
</script>

<style scoped>
  .wrapper4TransArea {
    width: 100%;
    height: 100%;
  }

  .wrapper4TransArea .toolArea {
    height: 42px;
    padding: 10px 10px 0 10px;
    position: relative;
    box-sizing: border-box;
  }

  .wrapper4TransArea .toolArea .dropDownArea{
    position: absolute;
    top: 10px;
    left: 10px;
    z-index: 100;
  }
  .wrapper4TransArea .toolArea .buttonArea{
    float: right;
  }

  .wrapper4TransArea .textArea {
    height: calc(100% - 42px);
  }

  .textAreaLeft {
    float: left;
    width: 50%;
    padding: 10px;
    box-sizing: border-box;
    height: 100%;
  }

  .textAreaRight {
    float: right;
    width: 50%;
    padding: 10px;
    box-sizing: border-box;
    height: 100%;
  }

  .titleArea{
    height: 50px;
    background-color: #191c2d;
    border-top: 2px solid #000;
    padding-left: 10px;
    box-sizing: border-box;
    font-size: 22px;
    line-height: 50px;
    color: rgba(255,255,255,0.6);
  }

  .aceTextArea{
    height: calc(100% - 50px);
  }
</style>
