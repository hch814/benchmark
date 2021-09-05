function request()
  return wrk.format("GET","/users?pageNum="..math.random(1,1000).."&pageSize=5")
end