# URI和URL的区别

简单理解：URL（Uniform Resource Locator）是网址类型的 URI（Uniform Resource Identifier），相当于 URI 的子类，其访问和获取的是 html，而 URI 是资源定位符，前缀可以是 `file://, psql://, mongo://` 等，其中包含有 URL 的 `http://, https://` 的前缀

## URI

## URL
[What is a URL? - Learn web development | MDN](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/Web_mechanics/What_is_a_URL)

### Absolute URLs

**Full URL**
`https://developer.mozilla.org/en-US/docs/Learn`

**Implicit protocol URL**
`//developer.mozilla.org/en-US/docs/Learn`
使用 Implicit protocol URL 时，Browser 会使用和请求当前 HTML document 时相同的协议

**Implicit domain name URL**
`/en-US/docs/Learn`
使用 Implicit domain name URL 时，Browser 会使用和当前 HTML document 相同的协议和相同的域名

### Relative URLs

**Sub directory URL**
`skills/Infrastructure/Understanding_URLs`

**Parent directory URL**
`../css/display`

## 参考链接
1. [Uniform Resource Identifier - Wikipedia](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#URLs_and_URNs)
2. [URL - Wikipedia](https://en.wikipedia.org/wiki/URL)