package com.cebrains.hrc.core.base.tips;

/**
 * 返回给前台的成功提示
 *
 * @author frank
 * @date 2016年11月12日 下午5:05:22
 */
public class SuccessResultTip extends Tip {
	private Object data;

	public SuccessResultTip(){
		super.code = 200;
		super.message = "操作成功";
	}

	public SuccessResultTip(Object data){
		super.code = 200;
		super.message = "操作成功";
		this.data=data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
