function request()
  return wrk.format("GET","/mem/users?pageNum="..math.random(1,1000).."&pageSize=5")
