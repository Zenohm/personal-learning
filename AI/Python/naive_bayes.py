from sklearn.naive_bayes import GaussianNB

A = {"data":  [[0, 0, 0],
               [1, 1, 1],
               [2, 2, 2]],
     "target": [0, 1, 2]}

fit_object = GaussianNB()
y_pred = fit_object.fit(A['data'], A['target']).predict([[0, 0, 6]])
print("I predict " + str(y_pred))
