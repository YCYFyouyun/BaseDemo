package com.template.rxextention.mvp;


import com.template.base.mvp.IBaseView;

import io.reactivex.subjects.PublishSubject;

/**
 * RX MVP模版中View的公共接口
 */

public interface IRxBaseView extends IBaseView {
    PublishSubject<Integer> getLifecycleSubject();
}
